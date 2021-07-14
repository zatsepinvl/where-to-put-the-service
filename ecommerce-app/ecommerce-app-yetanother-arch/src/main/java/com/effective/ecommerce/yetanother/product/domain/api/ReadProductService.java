package com.effective.ecommerce.yetanother.product.domain.api;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
public interface ReadProductService {

    @NotNull Iterable<Product> getAllProducts();

    Product getProduct(@Min(value = 1L) long productId);

    boolean doesProductExist(long productId);
}
