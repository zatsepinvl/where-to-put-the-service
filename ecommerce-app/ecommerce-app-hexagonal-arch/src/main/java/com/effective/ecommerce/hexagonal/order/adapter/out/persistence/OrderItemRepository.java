package com.effective.ecommerce.hexagonal.order.adapter.out.persistence;

import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItemEntity, OrderItemPK> {
}
