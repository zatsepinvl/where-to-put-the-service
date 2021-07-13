package com.effective.ecommerce.hexagonal.order.application.port.in;

public record CreateOrderItemCommand(
        long productId,
        int quantity
) {
}
