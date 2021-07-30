package com.effective.ecommerce.clean.order.adapter.persistence.impl;

import com.effective.ecommerce.clean.order.adapter.persistence.jpa.OrderItemEntity;
import com.effective.ecommerce.clean.order.adapter.persistence.jpa.OrderItemPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItemEntity, OrderItemPK> {
}
