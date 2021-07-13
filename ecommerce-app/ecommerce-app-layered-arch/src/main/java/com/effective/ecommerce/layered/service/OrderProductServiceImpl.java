package com.effective.ecommerce.layered.service;

import com.effective.ecommerce.layered.model.OrderItem;
import com.effective.ecommerce.layered.repository.OrderProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

    private OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        return this.orderProductRepository.save(orderItem);
    }
}
