package com.effective.ecommerce.hexagonal.product.application.port.in;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record SaveProductCommand(
        Long id,
        @NotNull String name,
        @NotNull BigDecimal price,
        String pictureUrl
) {
}
