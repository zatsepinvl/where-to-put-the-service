package com.effective.ecommerce.yetanother;

import com.effective.ecommerce.yetanother.product.domain.api.command.CreateProductCommand;
import com.effective.ecommerce.yetanother.product.domain.api.WriteProductService;
import com.effective.ecommerce.yetanother.product.domain.impl.ProductEntity;
import com.effective.ecommerce.yetanother.product.domain.impl.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

/*
    Source: https://github.com/eugenp/tutorials/tree/master/spring-boot-modules/spring-boot-angular
 */
@SpringBootApplication
public class EcommerceAppYetAnother {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceAppYetAnother.class, args);
    }


    @Bean
    CommandLineRunner runner(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new ProductEntity(1L, "TV Set", BigDecimal.valueOf(300.51d), "http://placehold.it/200x100"));
            productRepository.save(new ProductEntity(2L, "Game Console", BigDecimal.valueOf(200), "http://placehold.it/200x100"));
            productRepository.save(new ProductEntity(3L, "Sofa", BigDecimal.valueOf(100), "http://placehold.it/200x100"));
            productRepository.save(new ProductEntity(4L, "Icecream", BigDecimal.valueOf(5), "http://placehold.it/200x100"));
            productRepository.save(new ProductEntity(5L, "Beer", BigDecimal.valueOf(3), "http://placehold.it/200x100"));
            productRepository.save(new ProductEntity(6L, "Phone", BigDecimal.valueOf(500), "http://placehold.it/200x100"));
            productRepository.save(new ProductEntity(7L, "Watch", BigDecimal.valueOf(30), "http://placehold.it/200x100"));
        };
    }
}
