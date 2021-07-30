package com.effective.ecommerce.clean.order.adapter.persistence.impl;

import com.effective.ecommerce.clean.order.adapter.persistence.jpa.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
}
