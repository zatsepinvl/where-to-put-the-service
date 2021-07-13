package com.effective.ecommerce.hexagonal.product.domain;

import java.math.BigDecimal;

public record Product(
        Long id,
        String name,
        BigDecimal price,
        String pictureUrl) {
}
