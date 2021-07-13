package com.effective.ecommerce.hexagonal.product.application.port.in;

import com.effective.ecommerce.hexagonal.product.domain.Product;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
public interface ReadProductUseCase {

    @NotNull Iterable<Product> getAllProducts();

    Product getProduct(@Min(value = 1L) long productId);

    boolean doesProductExist(long productId);
}
