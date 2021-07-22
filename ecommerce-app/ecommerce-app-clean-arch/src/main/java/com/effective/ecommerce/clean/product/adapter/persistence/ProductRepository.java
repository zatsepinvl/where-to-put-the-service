package com.effective.ecommerce.clean.product.adapter.persistence;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
