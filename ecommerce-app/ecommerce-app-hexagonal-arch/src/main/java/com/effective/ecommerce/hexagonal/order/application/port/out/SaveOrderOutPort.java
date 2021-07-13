package com.effective.ecommerce.hexagonal.order.application.port.out;

import com.effective.ecommerce.hexagonal.order.domain.Order;
import com.effective.ecommerce.hexagonal.order.domain.OrderItem;

import java.util.List;

public interface SaveOrderOutPort {
    Order saveOrder(Order order);

    List<OrderItem> saveOrderItems(Order order, List<OrderItem> orderItems);
}
