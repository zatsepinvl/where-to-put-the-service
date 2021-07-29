package com.effective.ecommerce.clean.order.domain.port.out;


import com.effective.ecommerce.clean.order.model.Order;

import java.util.List;
import java.util.Optional;

public interface ReadOrderOutPort {
    Optional<OrderData> getOrderDataById(long orderId);

    List<OrderItemData> getOrderItemsByOrderId(long orderId);
}
