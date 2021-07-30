package com.effective.ecommerce.clean.order.domain.port.out;

import com.effective.ecommerce.clean.order.model.OrderStatus;

import java.time.ZonedDateTime;

public record OrderData(
        long id,
        ZonedDateTime createdAt,
        OrderStatus status
) {
}
