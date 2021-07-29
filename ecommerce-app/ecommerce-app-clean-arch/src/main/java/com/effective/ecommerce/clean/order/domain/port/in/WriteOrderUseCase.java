package com.effective.ecommerce.clean.order.domain.port.in;

import com.effective.ecommerce.clean.order.model.Order;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface WriteOrderUseCase {
    Order createOrder(@NotNull @Valid CreateOrderCommand command);
}
