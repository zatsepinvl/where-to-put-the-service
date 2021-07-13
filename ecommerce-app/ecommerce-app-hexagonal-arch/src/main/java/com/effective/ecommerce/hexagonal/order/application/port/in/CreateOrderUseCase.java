package com.effective.ecommerce.hexagonal.order.application.port.in;

import com.effective.ecommerce.hexagonal.order.domain.OrderDescriptor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface CreateOrderUseCase {
    OrderDescriptor createOrder(@NotNull @Valid CreateOrderCommand command);
}
