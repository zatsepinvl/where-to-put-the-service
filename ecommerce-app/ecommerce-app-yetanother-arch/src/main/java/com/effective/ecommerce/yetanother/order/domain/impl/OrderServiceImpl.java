package com.effective.ecommerce.yetanother.order.domain.impl;

import com.effective.ecommerce.yetanother.exception.domain.ResourceNotFoundException;
import com.effective.ecommerce.yetanother.order.domain.api.*;
import com.effective.ecommerce.yetanother.product.domain.api.ReadProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional
class OrderServiceImpl implements WriteOrderService, ReadOrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ReadProductService readProductService;

    @Override
    public Order createOrder(CreateOrderCommand command) {
        var newOrderEntity = OrderEntity.builder()
                .status(OrderStatus.CREATED)
                .createdAt(ZonedDateTime.now())
                .build();
        final var orderEntity = orderRepository.save(newOrderEntity);
        var orderItemEntities = command.orderItems().stream()
                .map(createOrderItemCommand -> {
                    var productId = createOrderItemCommand.productId();
                    if (!readProductService.doesProductExist(productId)) {
                        throw new ResourceNotFoundException("Product not found with id " + productId);
                    }
                    var orderItemPk = new OrderItemPK(orderEntity.getId(), productId);
                    return new OrderItemEntity(orderItemPk, createOrderItemCommand.quantity());
                })
                .toList();
        orderItemEntities = (List<OrderItemEntity>) orderItemRepository.saveAll(orderItemEntities);
        orderEntity.setOrderItems(orderItemEntities);
        return fromOrderEntity(orderEntity);
    }

    @Override
    public Order getOrder(long orderId) {
        var orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));
        return fromOrderEntity(orderEntity);
    }

    private Order fromOrderEntity(OrderEntity orderEntity) {
        var orderItems = fromOrderItemEntities(orderEntity.getOrderItems());
        return new Order(
                orderEntity.getId(),
                orderEntity.getCreatedAt(),
                orderEntity.getStatus(),
                orderItems
        );
    }

    private List<OrderItem> fromOrderItemEntities(Iterable<OrderItemEntity> orderItems) {
        return StreamSupport.stream(orderItems.spliterator(), false)
                .map(orderItem -> {
                    var product = readProductService.getProduct(orderItem.getPk().getProductId());
                    return new OrderItem(product, orderItem.getQuantity());
                })
                .toList();
    }
}
