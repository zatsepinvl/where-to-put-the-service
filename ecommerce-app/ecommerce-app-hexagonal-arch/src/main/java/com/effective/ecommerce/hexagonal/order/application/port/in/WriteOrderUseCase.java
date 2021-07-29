package com.effective.ecommerce.hexagonal.order.application.port.in;

import com.effective.ecommerce.hexagonal.order.domain.Order;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface WriteOrderUseCase {
    Order createOrder(@NotNull @Valid CreateOrderCommand command);
}
