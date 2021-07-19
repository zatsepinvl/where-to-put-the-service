import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArchitectureTest {

    private static final String BASE_PACKAGE = "com.effective.ecommerce.yetanother";

    private final JavaClasses appClasses = new ClassFileImporter()
            .withImportOption(new ImportOption.DoNotIncludeTests())
            .importPackages(BASE_PACKAGE);

    @Test
    public void ensure_no_dep_on_package_rule_works() {
        var testClasses = new ClassFileImporter()
                .importPackages(BASE_PACKAGE + ".archunit.deps");
        var packageIdentifiers = getPackageIdentifiersFor("impl", testClasses);
        assertThrows(
                AssertionError.class,
                () -> packageIdentifiers.forEach(identifier -> ensureNoDependencyOnPackage(identifier, testClasses))
        );
    }

    @Test
    public void ensure_no_dependency_on_impl() {
        getPackageIdentifiersFor("impl").forEach(this::ensureNoDependencyOnPackage);
    }

    @Test
    public void ensure_no_dependency_on_web() {
        getPackageIdentifiersFor("web").forEach(this::ensureNoDependencyOnPackage);
    }


    @Test
    public void ensure_no_cycle_dependencies_in_root_packages() {
        ensure_no_cycle_dependencies(".(*)..");
    }

    @Test
    public void ensure_no_cycle_dependencies_in_sub_packages() {
        ensure_no_cycle_dependencies(".(**)..");
    }


    private void ensure_no_cycle_dependencies(String packageIdentifierSuffix) {
        ensure_no_cycle_dependencies(packageIdentifierSuffix, appClasses);
    }

    private void ensure_no_cycle_dependencies(String packageIdentifierSuffix, JavaClasses classes) {
        slices().matching(BASE_PACKAGE + packageIdentifierSuffix).should()
                .beFreeOfCycles()
                .check(classes);
    }

    private Stream<String> getPackageIdentifiersFor(String parentPackage) {
        return getPackageIdentifiersFor(parentPackage, appClasses);
    }

    private Stream<String> getPackageIdentifiersFor(String parentPackage, JavaClasses classes) {
        return classes.stream()
                .map(JavaClass::getPackageName)
                .filter(pack -> pack.endsWith(parentPackage))
                .map(pack -> pack + "..")
                .distinct();
    }

    private void ensureNoDependencyOnPackage(String packageIdentifier) {
        ensureNoDependencyOnPackage(packageIdentifier, appClasses);
    }

    private void ensureNoDependencyOnPackage(String packageIdentifier, JavaClasses classes) {
        var rule = classes().that().resideInAPackage(packageIdentifier)
                .should().onlyBeAccessed().byClassesThat().resideInAPackage(packageIdentifier);
        rule.check(classes);
    }
}
