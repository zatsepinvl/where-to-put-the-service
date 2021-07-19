package com.effective.ecommerce.yetanother.product.domain.impl;

import com.effective.ecommerce.yetanother.product.persistence.ProductEntity;
import org.springframework.data.repository.CrudRepository;

interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
