package com.effective.ecommerce.hexagonal.order.application.port.out;

public record OrderItemData(
        long productId,
        int quantity
) {

}
