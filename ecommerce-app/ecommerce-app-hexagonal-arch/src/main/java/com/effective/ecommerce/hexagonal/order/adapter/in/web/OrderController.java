package com.effective.ecommerce.hexagonal.order.adapter.in.web;

import com.effective.ecommerce.hexagonal.order.application.port.in.CreateOrderCommand;
import com.effective.ecommerce.hexagonal.order.application.port.in.WriteOrderUseCase;
import com.effective.ecommerce.hexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final WriteOrderUseCase createOrderUseCase;

   /* @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Order> list() {
        return this.orderService.getAllOrders();
    }*/

    @PostMapping
    @ResponseStatus(CREATED)
    public Order createOrder(@RequestBody CreateOrderCommand form) {
        return createOrderUseCase.createOrder(form);
    }


}
