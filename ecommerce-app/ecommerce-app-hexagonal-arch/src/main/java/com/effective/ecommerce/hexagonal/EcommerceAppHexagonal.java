package com.effective.ecommerce.hexagonal;

import com.effective.ecommerce.hexagonal.product.application.port.in.SaveProductCommand;
import com.effective.ecommerce.hexagonal.product.application.port.in.SaveProductUseCase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class EcommerceAppHexagonal {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceAppHexagonal.class, args);
    }


    @Bean
    CommandLineRunner runner(SaveProductUseCase createProductUseCase) {
        return args -> {
            createProductUseCase.save(new SaveProductCommand(1L, "TV Set", BigDecimal.valueOf(300.51d), "http://placehold.it/200x100"));
            createProductUseCase.save(new SaveProductCommand(2L, "Game Console", BigDecimal.valueOf(200), "http://placehold.it/200x100"));
            createProductUseCase.save(new SaveProductCommand(3L, "Sofa", BigDecimal.valueOf(100), "http://placehold.it/200x100"));
            createProductUseCase.save(new SaveProductCommand(4L, "Icecream", BigDecimal.valueOf(5), "http://placehold.it/200x100"));
            createProductUseCase.save(new SaveProductCommand(5L, "Beer", BigDecimal.valueOf(3), "http://placehold.it/200x100"));
            createProductUseCase.save(new SaveProductCommand(6L, "Phone", BigDecimal.valueOf(500), "http://placehold.it/200x100"));
            createProductUseCase.save(new SaveProductCommand(7L, "Watch", BigDecimal.valueOf(30), "http://placehold.it/200x100"));
        };
    }
}
