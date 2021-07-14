package com.effective.ecommerce.hexagonal.order.domain;

import java.time.LocalDate;

public record Order(
        long id,
        LocalDate dateCreated,
        OrderStatus status
) {
}
