package com.effective.ecommerce.hexagonal.order.domain;


import com.effective.ecommerce.hexagonal.product.domain.Product;

import java.math.BigDecimal;

public record OrderItem(
        Product product,
        int quantity
) {
    public BigDecimal getTotalPrice() {
        return product.price().multiply(BigDecimal.valueOf(quantity));
    }
}
