package com.effective.ecommerce.clean.order.domain.port.in;

public record CreateOrderItemCommand(
        long productId,
        int quantity
) {
}
