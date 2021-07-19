package com.effective.ecommerce.yetanother.product.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
class SaveProductRequest {
    @JsonIgnore
    long productId;
    @NotBlank String name;
    @NotNull BigDecimal price;
    String pictureUrl;
}
