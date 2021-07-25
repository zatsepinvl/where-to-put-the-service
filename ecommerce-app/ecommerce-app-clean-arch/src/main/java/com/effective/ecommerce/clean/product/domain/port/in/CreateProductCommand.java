package com.effective.ecommerce.clean.product.domain.port.in;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CreateProductCommand(
        @NotBlank String name,
        @NotNull BigDecimal price,
        String pictureUrl
) {
}
