package com.effective.ecommerce.clean.order.adapter.persistence.impl;

import com.effective.ecommerce.clean.order.adapter.persistence.jpa.OrderEntity;
import com.effective.ecommerce.clean.order.domain.port.out.OrderData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderEntityMapper {

    OrderEntity fromOrderData(OrderData order);

    OrderData toOrderData(OrderEntity orderEntity);
}
