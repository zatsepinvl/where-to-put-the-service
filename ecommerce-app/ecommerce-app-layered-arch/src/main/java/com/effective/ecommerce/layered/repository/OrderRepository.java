package com.effective.ecommerce.layered.repository;

import com.effective.ecommerce.layered.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
