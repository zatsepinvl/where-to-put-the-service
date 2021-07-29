package com.effective.ecommerce.hexagonal.order.application.port.in;

import com.effective.ecommerce.hexagonal.order.domain.Order;

public interface ReadOrderUseCase {
    Order getOrderDescriptor(long orderId);
}
