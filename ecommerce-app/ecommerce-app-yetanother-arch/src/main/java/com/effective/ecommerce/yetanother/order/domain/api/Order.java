package com.effective.ecommerce.yetanother.order.domain.api;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static java.math.BigDecimal.ZERO;

public record Order(
        @NotNull long id,
        @NotNull ZonedDateTime createdAt,
        @NotNull OrderStatus status,
        @NotNull List<OrderItem> orderItems
) {
    public BigDecimal getTotalOrderPrice() {
        BigDecimal sum = ZERO;
        for (OrderItem op : orderItems) {
            sum = sum.add(op.getTotalPrice());
        }
        return sum;
    }

    public int getNumberOfProducts() {
        return this.orderItems.size();
    }
}