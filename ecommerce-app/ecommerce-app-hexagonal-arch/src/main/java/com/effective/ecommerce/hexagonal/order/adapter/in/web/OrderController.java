package com.effective.ecommerce.hexagonal.order.adapter.in.web;

import com.effective.ecommerce.hexagonal.order.application.port.in.CreateOrderCommand;
import com.effective.ecommerce.hexagonal.order.application.port.in.CreateOrderUseCase;
import com.effective.ecommerce.hexagonal.order.domain.OrderDescriptor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

   /* @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Order> list() {
        return this.orderService.getAllOrders();
    }*/

    @PostMapping
    @ResponseStatus(CREATED)
    public OrderDescriptor createOrder(@RequestBody CreateOrderCommand form) {
        return createOrderUseCase.createOrder(form);
    }


}
