package com.effective.ecommerce.yetanother.product.domain.api.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record UpdateProductCommand(
        long productId,
        @NotBlank String name,
        @NotNull BigDecimal price,
        String pictureUrl
) {
}
