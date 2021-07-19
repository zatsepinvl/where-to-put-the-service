package com.effective.ecommerce.yetanother.order.domain.api;


import com.effective.ecommerce.yetanother.product.domain.api.Product;

import java.math.BigDecimal;

public record OrderItem(
        Product product,
        int quantity
) {
    public BigDecimal getTotalPrice() {
        return product.price().multiply(BigDecimal.valueOf(quantity));
    }
}
