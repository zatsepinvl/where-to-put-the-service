package com.effective.ecommerce.yetanother.order.domain.impl;

import com.effective.ecommerce.yetanother.order.persistence.OrderItemEntity;
import com.effective.ecommerce.yetanother.order.persistence.OrderItemPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItemEntity, OrderItemPK> {
}
