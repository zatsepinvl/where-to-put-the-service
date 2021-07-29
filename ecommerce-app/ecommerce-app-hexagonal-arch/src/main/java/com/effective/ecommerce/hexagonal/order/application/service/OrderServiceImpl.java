package com.effective.ecommerce.hexagonal.order.application.service;

import com.effective.ecommerce.hexagonal.exception.application.ResourceNotFoundException;
import com.effective.ecommerce.hexagonal.order.application.port.in.CreateOrderCommand;
import com.effective.ecommerce.hexagonal.order.application.port.in.WriteOrderUseCase;
import com.effective.ecommerce.hexagonal.order.application.port.in.ReadOrderUseCase;
import com.effective.ecommerce.hexagonal.order.application.port.out.OrderData;
import com.effective.ecommerce.hexagonal.order.application.port.out.OrderItemData;
import com.effective.ecommerce.hexagonal.order.application.port.out.SaveOrderOutPort;
import com.effective.ecommerce.hexagonal.order.domain.*;
import com.effective.ecommerce.hexagonal.product.application.port.in.ReadProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
class OrderServiceImpl implements WriteOrderUseCase, ReadOrderUseCase {

    private final ReadProductUseCase readProductUseCase;
    private final SaveOrderOutPort saveOrderOutPort;
    //private final ReadOrderOutPort readOrderOutPort;

    @Override
    public Order createOrder(CreateOrderCommand command) {
        var newOrder = new OrderData(-1, LocalDate.now(), OrderStatus.CREATED);
        final var order = saveOrderOutPort.saveOrderData(newOrder);
        var orderItemsData = command.orderItems().stream()
                .map(createOrderItemCommand -> {
                    var productId = createOrderItemCommand.productId();
                    if (!readProductUseCase.doesProductExist(productId)) {
                        throw new ResourceNotFoundException("Product not found with id " + productId);
                    }
                    return new OrderItemData(productId, createOrderItemCommand.quantity());
                })
                .toList();
        orderItemsData = saveOrderOutPort.saveOrderItemsData(order, orderItemsData);
        var orderItemDescriptors = getOrderItems(orderItemsData);
        return new Order(order.id(), order.dateCreated(), order.status(), orderItemDescriptors);
    }

    @Override
    public Order getOrderDescriptor(long orderId) {
        //ToDo
        return null;
    }

    private List<OrderItem> getOrderItems(List<OrderItemData> orderItemsData) {
        return orderItemsData.stream()
                .map(orderItem -> {
                    var product = readProductUseCase.getProduct(orderItem.productId());
                    return new OrderItem(product, orderItem.quantity());
                })
                .toList();
    }
}
