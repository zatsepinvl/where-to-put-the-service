package com.effective.ecommerce.hexagonal.product.adapter.out.persistence;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
