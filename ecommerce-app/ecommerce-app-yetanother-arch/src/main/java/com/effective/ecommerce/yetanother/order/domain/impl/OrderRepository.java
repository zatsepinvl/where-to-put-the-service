package com.effective.ecommerce.yetanother.order.domain.impl;

import org.springframework.data.repository.CrudRepository;

interface OrderRepository extends CrudRepository<OrderEntity, Long> {
}
