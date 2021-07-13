package com.effective.ecommerce.hexagonal;

import com.effective.ecommerce.hexagonal.order.application.port.in.CreateOrderCommand;
import com.effective.ecommerce.hexagonal.order.application.port.in.CreateOrderItemCommand;
import com.effective.ecommerce.hexagonal.order.domain.OrderDescriptor;
import com.effective.ecommerce.hexagonal.order.domain.OrderStatus;
import com.effective.ecommerce.hexagonal.product.domain.Product;
import com.effective.ecommerce.testutils.EnablePostgresTestcontainer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnablePostgresTestcontainer
public class EcommerceAppHexagonalIntTest {

    private static final String BASE_URL = "http://localhost";

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void givenGetProductsApiCall_whenProductListRetrieved_thenSizeMatchAndListContainsProductNames() {
        ResponseEntity<Iterable<Product>> responseEntity = restTemplate.exchange(
                severUrl() + "/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        Iterable<Product> products = responseEntity.getBody();
        Assertions
                .assertThat(products)
                .hasSize(7);

        assertThat(products, hasItem(hasProperty("name", is("TV Set"))));
        assertThat(products, hasItem(hasProperty("name", is("Game Console"))));
        assertThat(products, hasItem(hasProperty("name", is("Sofa"))));
        assertThat(products, hasItem(hasProperty("name", is("Icecream"))));
        assertThat(products, hasItem(hasProperty("name", is("Beer"))));
        assertThat(products, hasItem(hasProperty("name", is("Phone"))));
        assertThat(products, hasItem(hasProperty("name", is("Watch"))));
    }

    @Test
    public void givenGetOrdersApiCall_whenProductListRetrieved_thenSizeMatchAndListContainsProductNames() {
        ResponseEntity<Iterable<OrderDescriptor>> responseEntity = restTemplate.exchange(
                severUrl() + "/orders",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        Iterable<OrderDescriptor> orders = responseEntity.getBody();
        Assertions
                .assertThat(orders)
                .hasSize(0);
    }

    @Test
    public void givenPostOrder_whenBodyRequestMatcherJson_thenResponseContainsEqualObjectProperties() {
        final ResponseEntity<OrderDescriptor> postResponse = restTemplate.postForEntity(
                severUrl() + "/orders",
                prepareOrderForm(),
                OrderDescriptor.class
        );
        OrderDescriptor orderDescriptor = postResponse.getBody();

        Assertions
                .assertThat(postResponse.getStatusCode())
                .isEqualByComparingTo(HttpStatus.CREATED);

        assertThat(orderDescriptor, notNullValue());
        assertThat(orderDescriptor.order().status(),  is(OrderStatus.CREATED));
        assertThat(orderDescriptor.orderItems().get(0).quantity(), is(2));
    }

    private String severUrl() {
        return BASE_URL + ":" + port;
    }

    private CreateOrderCommand prepareOrderForm() {
        var orderItemCommand = new CreateOrderItemCommand(1L, 2);
        return new CreateOrderCommand(Collections.singletonList(orderItemCommand));
    }
}
