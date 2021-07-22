package com.effective.ecommerce.yetanother;

import com.effective.ecommerce.yetanother.product.domain.api.Product;
import com.effective.ecommerce.yetanother.product.domain.api.ReadProductService;
import com.effective.ecommerce.yetanother.product.domain.api.WriteProductService;
import com.effective.ecommerce.yetanother.product.web.ProductController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = ProductController.class)
@ContextConfiguration
public abstract class ProductContractTestBase {

    @TestConfiguration
    @ComponentScan("com.effective.ecommerce.yetanother.product.web")
    public static class ContractTestConfig {
    }

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ReadProductService readProductService;

    @MockBean
    WriteProductService writeProductService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);

        Mockito.when(writeProductService.createProduct(any()))
                .thenReturn(new Product(
                        1L,
                        "iPhone",
                        new BigDecimal("300.51"),
                        "http://placehold.it/200x100"
                ));
    }

}
