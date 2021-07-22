package com.effective.ecommerce.hexagonal.product.application.port.out;

import com.effective.ecommerce.hexagonal.product.domain.Product;

public interface SaveProductOutPort {
    Product save(Product product);
}
