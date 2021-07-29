package com.effective.ecommerce.hexagonal.order.application.port.out;

import com.effective.ecommerce.hexagonal.order.domain.OrderStatus;

import java.time.LocalDate;

public record OrderData(
        long id,
        LocalDate dateCreated,
        OrderStatus status
) {
}
