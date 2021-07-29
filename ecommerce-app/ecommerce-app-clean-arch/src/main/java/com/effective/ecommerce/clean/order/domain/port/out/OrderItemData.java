package com.effective.ecommerce.clean.order.domain.port.out;

public record OrderItemData(
        long productId,
        int quantity
) {

}
