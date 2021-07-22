package com.effective.ecommerce.clean.product.usecase.port.out;


import com.effective.ecommerce.clean.product.entity.Product;

public interface SaveProductOutPort {
    Product save(Product product);
}
