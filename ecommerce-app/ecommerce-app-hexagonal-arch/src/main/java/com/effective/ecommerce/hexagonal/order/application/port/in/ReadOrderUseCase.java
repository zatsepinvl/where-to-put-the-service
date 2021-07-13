package com.effective.ecommerce.hexagonal.order.application.port.in;

import com.effective.ecommerce.hexagonal.order.domain.OrderDescriptor;

public interface ReadOrderUseCase {
    OrderDescriptor getOrderDescriptor(long orderId);
}
