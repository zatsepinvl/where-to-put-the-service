package com.effective.ecommerce.clean.order.domain.port.in;


import com.effective.ecommerce.clean.order.model.Order;

public interface ReadOrderUseCase {
    Order getOrderById(long orderId);
}
