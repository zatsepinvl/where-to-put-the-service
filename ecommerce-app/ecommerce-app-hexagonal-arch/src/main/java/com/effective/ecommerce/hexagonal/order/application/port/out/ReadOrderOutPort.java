package com.effective.ecommerce.hexagonal.order.application.port.out;

import java.util.List;
import java.util.Optional;

public interface ReadOrderOutPort {
    Optional<OrderData> getOrderById(long orderId);

    List<OrderItemData> getOrderItemsByOrderId(long orderId);
}
