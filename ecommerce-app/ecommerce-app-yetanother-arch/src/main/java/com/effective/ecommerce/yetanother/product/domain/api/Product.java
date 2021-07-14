package com.effective.ecommerce.yetanother.product.domain.api;

import java.math.BigDecimal;

public record Product(
        Long id,
        String name,
        BigDecimal price,
        String pictureUrl) {
}
