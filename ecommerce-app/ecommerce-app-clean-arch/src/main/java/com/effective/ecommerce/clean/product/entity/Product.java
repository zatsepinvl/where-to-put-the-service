package com.effective.ecommerce.clean.product.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record Product(
        Long id,
        String name,
        BigDecimal price,
        String pictureUrl,
        ZonedDateTime createdAt) {
}
