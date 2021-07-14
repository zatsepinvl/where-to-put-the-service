package com.effective.ecommerce.hexagonal.order.application.service;

import com.effective.ecommerce.hexagonal.exception.application.ResourceNotFoundException;
import com.effective.ecommerce.hexagonal.order.application.port.in.CreateOrderCommand;
import com.effective.ecommerce.hexagonal.order.application.port.in.CreateOrderUseCase;
import com.effective.ecommerce.hexagonal.order.application.port.in.ReadOrderUseCase;
import com.effective.ecommerce.hexagonal.order.application.port.out.ReadOrderOutPort;
import com.effective.ecommerce.hexagonal.order.application.port.out.SaveOrderOutPort;
import com.effective.ecommerce.hexagonal.order.domain.*;
import com.effective.ecommerce.hexagonal.product.application.port.in.ReadProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
class OrderServiceImpl implements CreateOrderUseCase, ReadOrderUseCase {

    private final ReadProductUseCase readProductUseCase;
    private final SaveOrderOutPort saveOrderOutPort;
    //private final ReadOrderOutPort readOrderOutPort;

    @Override
    public OrderDescriptor createOrder(CreateOrderCommand command) {
        var newOrder = new Order(-1, LocalDate.now(), OrderStatus.CREATED);
        final var order = saveOrderOutPort.saveOrder(newOrder);
        var orderItems = command.orderItems().stream()
                .map(createOrderItemCommand -> {
                    var productId = createOrderItemCommand.productId();
                    if (!readProductUseCase.doesProductExist(productId)) {
                        throw new ResourceNotFoundException("Product not found with id " + productId);
                    }
                    return new OrderItem(productId, createOrderItemCommand.quantity());
                })
                .toList();
        orderItems = saveOrderOutPort.saveOrderItems(order, orderItems);
        var orderItemDescriptors = getOrderItemDescriptors(orderItems);
        return new OrderDescriptor(order.id(), order.dateCreated(), order.status(), orderItemDescriptors);
    }

    @Override
    public OrderDescriptor getOrderDescriptor(long orderId) {
        //ToDo
        return null;
    }

    private List<OrderItemDescriptor> getOrderItemDescriptors(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem -> {
                    var product = readProductUseCase.getProduct(orderItem.productId());
                    return new OrderItemDescriptor(product, orderItem.quantity());
                })
                .toList();
    }
}
