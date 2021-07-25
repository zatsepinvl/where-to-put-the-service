package com.effective.ecommerce.clean.product.domain.port.out;


import com.effective.ecommerce.clean.product.model.Product;

import java.util.Optional;

public interface ReadProductOutPort {
    Iterable<Product> getAllProducts();

    Optional<Product> getProductById(long productId);

    boolean exists(long productId);
}
