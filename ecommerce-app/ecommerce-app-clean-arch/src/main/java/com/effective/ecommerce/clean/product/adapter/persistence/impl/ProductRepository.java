package com.effective.ecommerce.clean.product.adapter.persistence.impl;

import com.effective.ecommerce.clean.product.adapter.persistence.jpa.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
