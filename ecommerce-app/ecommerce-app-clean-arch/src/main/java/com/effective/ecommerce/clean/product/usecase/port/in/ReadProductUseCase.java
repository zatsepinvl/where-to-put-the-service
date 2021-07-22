package com.effective.ecommerce.clean.product.usecase.port.in;

import com.effective.ecommerce.clean.product.entity.Product;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
public interface ReadProductUseCase {

    @NotNull Iterable<Product> getAllProducts();

    Product getProduct(@Min(value = 1L) long productId);

    boolean doesProductExist(long productId);
}
