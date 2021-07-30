package com.effective.ecommerce.clean.order.domain.port.out;


import java.util.List;

public interface WriteOrderOutPort {
    OrderData createOrderData(OrderData orderData);

    List<OrderItemData> saveOrderItemsData(OrderData order, List<OrderItemData> orderItems);
}
