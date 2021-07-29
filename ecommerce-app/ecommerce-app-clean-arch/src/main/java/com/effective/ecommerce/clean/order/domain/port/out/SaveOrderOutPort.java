package com.effective.ecommerce.clean.order.domain.port.out;


import java.util.List;

public interface SaveOrderOutPort {
    OrderData saveOrder(OrderData order);

    List<OrderItemData> saveOrderItemsData(OrderData order, List<OrderItemData> orderItems);
}
