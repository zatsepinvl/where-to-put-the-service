package com.effective.ecommerce.clean.order.adapter.web;

import com.effective.ecommerce.clean.order.domain.port.in.CreateOrderCommand;
import com.effective.ecommerce.clean.order.domain.port.in.ReadOrderUseCase;
import com.effective.ecommerce.clean.order.domain.port.in.WriteOrderUseCase;
import com.effective.ecommerce.clean.order.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final ReadOrderUseCase readOrderUseCase;
    private final WriteOrderUseCase writeOrderUseCase;

    @PostMapping
    @ResponseStatus(CREATED)
    public Order createOrder(@Valid @RequestBody CreateOrderCommand command) {
        return writeOrderUseCase.createOrder(command);
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable long orderId) {
        return readOrderUseCase.getOrderById(orderId);
    }
}
