package com.effective.ecommerce.hexagonal.product.application.port.in;

import com.effective.ecommerce.hexagonal.product.domain.Product;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface SaveProductUseCase {
    /**
     * Creates new or updates existing product found by id.
     */
    Product save(@NotNull @Valid SaveProductCommand command);
}
