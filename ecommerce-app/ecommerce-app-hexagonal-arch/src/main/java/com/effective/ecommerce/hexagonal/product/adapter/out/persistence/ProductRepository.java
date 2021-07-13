package com.effective.ecommerce.hexagonal.product.adapter.out.persistence;

import com.effective.ecommerce.hexagonal.product.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
