package com.effective.ecommerce.hexagonal.order.domain;

import java.time.LocalDate;

public record Order(
        Long id,
        LocalDate dateCreated,
        OrderStatus status
) {
}
