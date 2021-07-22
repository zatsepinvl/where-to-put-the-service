package com.effective.ecommerce.clean.product.usecase.port.in;

import com.effective.ecommerce.clean.product.entity.Product;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface WriteProductUseCase {

    Product createProduct(@NotNull @Valid CreateProductCommand createCommand);

    Product updateProduct(@NotNull @Valid UpdateProductCommand updateCommand);
}
