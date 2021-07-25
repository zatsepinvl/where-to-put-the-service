package com.effective.ecommerce.clean;

import com.effective.ecommerce.clean.product.model.Product;
import com.effective.ecommerce.clean.product.usecase.port.in.CreateProductCommand;
import com.effective.ecommerce.clean.testutils.postgres.EnablePostgresTestcontainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static com.effective.ecommerce.clean.testutils.http.RequestSpec.givenHttpRequest;
import static com.effective.ecommerce.clean.testutils.http.ResponseSpec.StatusCode.STATUS_200;
import static com.effective.ecommerce.clean.testutils.http.ResponseSpec.StatusCode.STATUS_201;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@SpringBootTest
@AutoConfigureMockMvc
@EnablePostgresTestcontainer
public class EcommerceAppCleanTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void create_product() throws Exception {
        // Given
        var command = givenCreateProductCommand();
        var request = givenHttpRequest(mockMvc)
                .andPath(POST, "/products")
                .andBody(command);

        // When
        var response = request.whenMakeRequest();

        // Then
        var product = response
                .thenStatusShouldBe(STATUS_201)
                .getBody(Product.class);
        assertThat(product.name()).isEqualTo(command.name());
        assertThat(product.id()).isGreaterThan(0);
    }

    @Test
    public void create_and_get_product() throws Exception {
        // Given
        var command = givenCreateProductCommand();
        var expectedProduct = givenHttpRequest(mockMvc)
                .andPath(POST, "/products").andBody(command)
                .makeRequest()
                .getBody(Product.class);
        var request = givenHttpRequest(mockMvc)
                .andPath(GET, "/products/" + expectedProduct.id());

        // When
        var response = request.whenMakeRequest();

        // Then
        var actualProduct = response
                .thenStatusShouldBe(STATUS_200)
                .getBody(Product.class);
        assertThat(actualProduct).isEqualTo(expectedProduct);
    }


    private CreateProductCommand givenCreateProductCommand() {
        return new CreateProductCommand(
                "test_product",
                new BigDecimal("777.777"),
                null
        );
    }
}
