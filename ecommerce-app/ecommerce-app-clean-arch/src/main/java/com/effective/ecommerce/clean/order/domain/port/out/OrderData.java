package com.effective.ecommerce.clean.order.domain.port.out;

import com.effective.ecommerce.clean.order.model.OrderStatus;

import java.time.LocalDate;

public record OrderData(
        long id,
        LocalDate dateCreated,
        OrderStatus status
) {
}
