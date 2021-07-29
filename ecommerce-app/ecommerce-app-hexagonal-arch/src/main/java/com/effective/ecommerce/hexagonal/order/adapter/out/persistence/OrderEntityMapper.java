package com.effective.ecommerce.hexagonal.order.adapter.out.persistence;

import com.effective.ecommerce.hexagonal.order.application.port.out.OrderData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderEntityMapper {

    OrderEntity fromOrder(OrderData order);

    OrderData toOrder(OrderEntity orderEntity);
}
