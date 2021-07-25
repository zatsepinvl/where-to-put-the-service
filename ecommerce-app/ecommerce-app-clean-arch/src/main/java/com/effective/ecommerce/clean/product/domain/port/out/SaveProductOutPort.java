package com.effective.ecommerce.clean.product.domain.port.out;


import com.effective.ecommerce.clean.product.model.Product;

public interface SaveProductOutPort {
    Product save(Product product);
}
