package com.effective.ecommerce.hexagonal.order.domain;

import com.effective.ecommerce.hexagonal.product.domain.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

public record OrderItem(
        long productId,
        int quantity
) {

}
