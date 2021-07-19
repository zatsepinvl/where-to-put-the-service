package com.effective.ecommerce.yetanother.testutils.postgres;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@TestPropertySource(properties = {"test.datasource=postgres-testcontainer"})
@Import({PostgresContainerTestConfig.class})
public @interface EnablePostgresTestcontainer {
}
