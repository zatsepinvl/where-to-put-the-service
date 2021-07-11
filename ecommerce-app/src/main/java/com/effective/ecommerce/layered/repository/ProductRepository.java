package com.effective.ecommerce.layered.repository;

import com.effective.ecommerce.layered.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
