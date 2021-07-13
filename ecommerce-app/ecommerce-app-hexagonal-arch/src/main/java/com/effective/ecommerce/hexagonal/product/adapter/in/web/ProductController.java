package com.effective.ecommerce.hexagonal.product.adapter.in.web;

import com.effective.ecommerce.hexagonal.product.application.port.in.ReadProductUseCase;
import com.effective.ecommerce.hexagonal.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ReadProductUseCase readProductUseCase;

    @GetMapping
    public @NotNull Iterable<Product> getProducts() {
        return readProductUseCase.getAllProducts();
    }
}
