package com.effective.ecommerce.hexagonal.product.application.port.out;

import com.effective.ecommerce.hexagonal.product.domain.Product;

import java.util.Optional;

public interface ReadProductOutPort {
    Iterable<Product> getAllProducts();

    Optional<Product> getProductById(long productId);

    boolean exists(long productId);
}
