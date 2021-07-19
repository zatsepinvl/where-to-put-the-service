package com.effective.ecommerce.yetanother.order.domain.impl;

import org.springframework.data.repository.CrudRepository;

interface OrderItemRepository extends CrudRepository<OrderItemEntity, OrderItemPK> {
}
