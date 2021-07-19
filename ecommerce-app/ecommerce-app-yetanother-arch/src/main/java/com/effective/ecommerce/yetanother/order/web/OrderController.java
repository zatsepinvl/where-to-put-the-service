package com.effective.ecommerce.yetanother.order.web;

import com.effective.ecommerce.yetanother.order.domain.api.CreateOrderCommand;
import com.effective.ecommerce.yetanother.order.domain.api.Order;
import com.effective.ecommerce.yetanother.order.domain.api.ReadOrderService;
import com.effective.ecommerce.yetanother.order.domain.api.WriteOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final WriteOrderService writeOrderService;
    private final ReadOrderService readOrderService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Order createOrder(@RequestBody CreateOrderCommand command) {
        return writeOrderService.createOrder(command);
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable long orderId) {
        return readOrderService.getOrder(orderId);
    }

}
