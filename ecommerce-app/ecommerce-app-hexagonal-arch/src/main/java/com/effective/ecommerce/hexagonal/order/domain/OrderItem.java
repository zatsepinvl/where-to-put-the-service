package com.effective.ecommerce.hexagonal.order.domain;

public record OrderItem(
        long productId,
        int quantity
) {

}
