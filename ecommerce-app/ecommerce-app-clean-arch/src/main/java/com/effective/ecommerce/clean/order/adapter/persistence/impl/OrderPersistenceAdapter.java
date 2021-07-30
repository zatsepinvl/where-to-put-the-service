package com.effective.ecommerce.clean.order.adapter.persistence.impl;

import com.effective.ecommerce.clean.order.adapter.persistence.jpa.OrderItemEntity;
import com.effective.ecommerce.clean.order.adapter.persistence.jpa.OrderItemPK;
import com.effective.ecommerce.clean.order.domain.port.out.OrderData;
import com.effective.ecommerce.clean.order.domain.port.out.OrderItemData;
import com.effective.ecommerce.clean.order.domain.port.out.ReadOrderOutPort;
import com.effective.ecommerce.clean.order.domain.port.out.WriteOrderOutPort;
import com.effective.ecommerce.clean.product.adapter.persistence.jpa.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
class OrderPersistenceAdapter implements ReadOrderOutPort, WriteOrderOutPort {
    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderData createOrderData(OrderData orderData) {
        var entity = orderEntityMapper.fromOrderData(orderData);
        entity.setId(null);
        entity = orderRepository.save(entity);
        return orderEntityMapper.toOrderData(entity);
    }

    @Override
    public List<OrderItemData> saveOrderItemsData(OrderData order, List<OrderItemData> orderItems) {
        var entitiesToSave = orderItems.stream()
                .map(orderItem -> {
                    var productEntity = new ProductEntity();
                    productEntity.setId(orderItem.productId());
                    var orderEntity = orderEntityMapper.fromOrderData(order);
                    var pk = new OrderItemPK(orderEntity, productEntity);
                    return new OrderItemEntity(pk, orderItem.quantity());
                })
                .toList();
        var entities = orderItemRepository.saveAll(entitiesToSave);
        return StreamSupport.stream(entities.spliterator(), false)
                .map(entity -> new OrderItemData(entity.getPk().getProduct().getId(), entity.getQuantity()))
                .toList();
    }

    @Override
    public Optional<OrderData> getOrderDataById(long orderId) {
        var entity = orderRepository.findById(orderId);
        return entity.map(orderEntityMapper::toOrderData);
    }

    @Override
    public List<OrderItemData> getOrderItemsByOrderId(long orderId) {
        return null;
    }
}
