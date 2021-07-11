package com.effective.ecommerce.layered.service;

import com.effective.ecommerce.layered.model.OrderItem;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface OrderProductService {

    OrderItem create(@NotNull(message = "The products for order cannot be null.") @Valid OrderItem orderItem);
}
