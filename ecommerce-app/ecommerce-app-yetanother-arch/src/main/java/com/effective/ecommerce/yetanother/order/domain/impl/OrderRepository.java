package com.effective.ecommerce.yetanother.order.domain.impl;

import com.effective.ecommerce.yetanother.order.persistence.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
}
