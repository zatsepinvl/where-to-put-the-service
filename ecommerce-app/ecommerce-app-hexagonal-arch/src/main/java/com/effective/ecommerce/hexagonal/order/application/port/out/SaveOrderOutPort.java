package com.effective.ecommerce.hexagonal.order.application.port.out;

import java.util.List;

public interface SaveOrderOutPort {
    OrderData saveOrderData(OrderData order);

    List<OrderItemData> saveOrderItemsData(OrderData order, List<OrderItemData> orderItems);
}
