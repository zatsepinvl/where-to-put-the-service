package com.effective.ecommerce.hexagonal.order.domain;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

public record OrderDescriptor(
        @NotNull Order order,
        @NotNull List<OrderItemDescriptor> orderItems
) {
    public BigDecimal getTotalOrderPrice() {
        BigDecimal sum = ZERO;
        for (OrderItemDescriptor op : orderItems) {
            sum = sum.add(op.getTotalPrice());
        }
        return sum;
    }

    public int getNumberOfProducts() {
        return this.orderItems.size();
    }
}