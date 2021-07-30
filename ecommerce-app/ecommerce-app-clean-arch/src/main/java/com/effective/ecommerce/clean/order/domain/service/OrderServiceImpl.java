package com.effective.ecommerce.clean.order.domain.service;

import com.effective.ecommerce.clean.order.domain.port.in.CreateOrderCommand;
import com.effective.ecommerce.clean.order.domain.port.in.ReadOrderUseCase;
import com.effective.ecommerce.clean.order.domain.port.in.WriteOrderUseCase;
import com.effective.ecommerce.clean.order.domain.port.out.OrderData;
import com.effective.ecommerce.clean.order.domain.port.out.OrderItemData;
import com.effective.ecommerce.clean.order.domain.port.out.ReadOrderOutPort;
import com.effective.ecommerce.clean.order.domain.port.out.WriteOrderOutPort;
import com.effective.ecommerce.clean.order.model.Order;
import com.effective.ecommerce.clean.order.model.OrderItem;
import com.effective.ecommerce.clean.order.model.OrderStatus;
import com.effective.ecommerce.clean.product.domain.port.in.ReadProductUseCase;
import com.effective.ecommerce.hexagonal.exception.application.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
class OrderServiceImpl implements WriteOrderUseCase, ReadOrderUseCase {

    private final ReadProductUseCase readProductUseCase;
    private final WriteOrderOutPort saveOrderOutPort;
    private final ReadOrderOutPort readOrderOutPort;

    @Override
    public Order createOrder(CreateOrderCommand command) {
        var newOrder = new OrderData(-1, ZonedDateTime.now(), OrderStatus.CREATED);
        var orderData = saveOrderOutPort.createOrderData(newOrder);
        var orderItemsData = command.orderItems().stream()
                .map(createOrderItemCommand -> {
                    var productId = createOrderItemCommand.productId();
                    if (!readProductUseCase.doesProductExist(productId)) {
                        throw new ResourceNotFoundException("Product not found with id " + productId);
                    }
                    return new OrderItemData(productId, createOrderItemCommand.quantity());
                })
                .toList();
        orderItemsData = saveOrderOutPort.saveOrderItemsData(orderData, orderItemsData);
        var orderItems = getOrderItems(orderItemsData);
        return createOrderFromData(orderData, orderItems);
    }

    @Override
    //ToDo dont really like the way how it works, maybe will change in the future.
    public Order getOrderById(long orderId) {
        var orderData = readOrderOutPort.getOrderDataById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));
        var orderItemsData = readOrderOutPort.getOrderItemsByOrderId(orderId);
        var orderItems = getOrderItems(orderItemsData);
        return createOrderFromData(orderData, orderItems);
    }

    private Order createOrderFromData(OrderData orderData, List<OrderItem> orderItems) {
        return new Order(
                orderData.id(),
                orderData.createdAt(),
                orderData.status(),
                orderItems
        );
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
