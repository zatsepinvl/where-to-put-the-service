package com.effective.ecommerce.clean;

import com.effective.ecommerce.clean.order.domain.port.in.CreateOrderCommand;
import com.effective.ecommerce.clean.order.domain.port.in.CreateOrderItemCommand;
import com.effective.ecommerce.clean.order.model.Order;
import com.effective.ecommerce.clean.product.domain.port.in.CreateProductCommand;
import com.effective.ecommerce.clean.product.model.Product;
import com.effective.ecommerce.clean.testutils.postgres.EnablePostgresTestcontainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.effective.ecommerce.clean.testutils.http.RequestSpec.givenHttpRequest;
import static com.effective.ecommerce.clean.testutils.http.ResponseSpec.StatusCode.STATUS_200;
import static com.effective.ecommerce.clean.testutils.http.ResponseSpec.StatusCode.STATUS_201;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@SpringBootTest
@AutoConfigureMockMvc
@EnablePostgresTestcontainer
public class EcommerceAppCleanTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void create_product() throws Exception {
        // Given
        var command = givenDefaultProductCreateCommand();
        var request = givenHttpRequest(mockMvc, mapper)
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
        var command = givenDefaultProductCreateCommand();
        var expectedProduct = givenHttpRequest(mockMvc, mapper)
                .andPath(POST, "/products").andBody(command)
                .makeRequest()
                .getBody(Product.class);
        var request = givenHttpRequest(mockMvc, mapper)
                .andPath(GET, "/products/" + expectedProduct.id());

        // When
        var response = request.whenMakeRequest();

        // Then
        var actualProduct = response
                .thenStatusShouldBe(STATUS_200)
                .getBody(Product.class);
        assertThat(actualProduct).isEqualTo(expectedProduct);
    }

    @Test
    public void create_order() throws Exception {
        // Given
        var product1 = givenCreatedProduct("iPhone", ONE);
        var product2 = givenCreatedProduct("iPad", TEN);
        var createOrderCommand = givenCreateOrderCommand(
                givenCreateOrderItem(product1.id(), 1),
                givenCreateOrderItem(product2.id(), 10)
        );

        var request = givenHttpRequest(mockMvc, mapper)
                .andPath(POST, "/orders")
                .andBody(createOrderCommand);

        // When
        var response = request.whenMakeRequest();

        // Then
        var order = response
                .thenStatusShouldBe(STATUS_201)
                .getBody(Order.class);
        assertThat(order.orderItems()).hasSize(2);
        assertThat(order.getTotalOrderPrice())
                .isEqualTo(ONE.multiply(ONE).add(TEN.multiply(TEN)));
    }


    private CreateProductCommand givenDefaultProductCreateCommand() {
        return givenProductCreateCommand("test_product", new BigDecimal("777.777"));
    }

    private CreateProductCommand givenProductCreateCommand(String productName, BigDecimal price) {
        return new CreateProductCommand(
                productName,
                price,
                null
        );
    }

    private Product givenCreatedProduct(String productName, BigDecimal price) throws Exception {
        var command = givenProductCreateCommand(productName, price);
        return givenHttpRequest(mockMvc, mapper)
                .andPath(POST, "/products").andBody(command)
                .makeRequest()
                .getBody(Product.class);
    }

    private Product givenCreatedProduct(CreateProductCommand command) throws Exception {
        return givenHttpRequest(mockMvc, mapper)
                .andPath(POST, "/products").andBody(command)
                .makeRequest()
                .getBody(Product.class);
    }

    private CreateOrderItemCommand givenCreateOrderItem(long productId, int quantity) {
        return new CreateOrderItemCommand(
                productId, quantity
        );
    }

    private CreateOrderCommand givenCreateOrderCommand(CreateOrderItemCommand... orderItems) {
        return new CreateOrderCommand(
                Arrays.stream(orderItems).toList()
        );
    }
}
