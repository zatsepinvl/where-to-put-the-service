package com.effective.ecommerce.clean.product.usecase.port.out;


import com.effective.ecommerce.clean.product.entity.Product;

import java.util.Optional;

public interface ReadProductOutPort {
    Iterable<Product> getAllProducts();

    Optional<Product> getProductById(long productId);

    boolean exists(long productId);
}
