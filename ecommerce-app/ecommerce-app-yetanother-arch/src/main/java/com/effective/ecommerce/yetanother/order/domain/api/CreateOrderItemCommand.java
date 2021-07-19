package com.effective.ecommerce.yetanother.order.domain.api;

public record CreateOrderItemCommand(
        long productId,
        int quantity
) {
}
