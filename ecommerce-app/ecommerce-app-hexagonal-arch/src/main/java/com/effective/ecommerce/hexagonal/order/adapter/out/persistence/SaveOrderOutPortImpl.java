package com.effective.ecommerce.hexagonal.order.adapter.out.persistence;

import com.effective.ecommerce.hexagonal.order.application.port.out.SaveOrderOutPort;
import com.effective.ecommerce.hexagonal.order.application.port.out.OrderData;
import com.effective.ecommerce.hexagonal.order.application.port.out.OrderItemData;
import com.effective.ecommerce.hexagonal.product.adapter.out.persistence.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class SaveOrderOutPortImpl implements SaveOrderOutPort {
    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderData saveOrderData(OrderData order) {
        var entity = orderEntityMapper.fromOrder(order);
        if (order.id() == -1) {
            entity.setId(null);
        }
        entity = orderRepository.save(entity);
        return orderEntityMapper.toOrder(entity);
    }

    @Override
    public List<OrderItemData> saveOrderItemsData(OrderData order, List<OrderItemData> orderItems) {
        var entitiesToSave = orderItems.stream()
                .map(orderItem -> {
                    var productEntity = new ProductEntity();
                    productEntity.setId(orderItem.productId());
                    var orderEntity = orderEntityMapper.fromOrder(order);
                    var pk = new OrderItemPK(orderEntity, productEntity);
                    return new OrderItemEntity(pk, orderItem.quantity());
                })
                .toList();
        var entities = orderItemRepository.saveAll(entitiesToSave);
        return StreamSupport.stream(entities.spliterator(), false)
                .map(entity -> {
                    return new OrderItemData(entity.getPk().getProduct().getId(), entity.getQuantity());
                }).toList();
    }
}
