package com.effective.ecommerce.hexagonal.order.application.port.out;

import com.effective.ecommerce.hexagonal.order.domain.Order;
import com.effective.ecommerce.hexagonal.order.domain.OrderItem;

import java.util.List;
import java.util.Optional;

public interface ReadOrderOutPort {
    Optional<Order> getOrderById(long orderId);

    List<OrderItem> getOrderItemsByOrderId(long orderId);
}
