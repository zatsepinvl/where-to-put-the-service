package com.effective.ecommerce.clean.order.model;


import com.effective.ecommerce.clean.product.model.Product;

import java.math.BigDecimal;

public record OrderItem(
        Product product,
        int quantity
) {
    public BigDecimal getTotalPrice() {
        return product.price().multiply(BigDecimal.valueOf(quantity));
    }
}
