package com.effective.ecommerce.yetanother.order.domain.api;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface WriteOrderService {
    Order createOrder(@NotNull @Valid CreateOrderCommand command);
}
