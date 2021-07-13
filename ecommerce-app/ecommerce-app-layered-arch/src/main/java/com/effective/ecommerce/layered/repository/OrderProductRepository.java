package com.effective.ecommerce.layered.repository;

import com.effective.ecommerce.layered.model.OrderItem;
import com.effective.ecommerce.layered.model.OrderItemPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderItem, OrderItemPK> {
}
