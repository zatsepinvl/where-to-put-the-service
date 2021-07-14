package com.effective.ecommerce.hexagonal.product.adapter.out.persistence;

import com.effective.ecommerce.hexagonal.product.domain.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

    ProductEntity fromProduct(Product product);

    Product toProduct(ProductEntity productEntity);

    Iterable<Product> toProducts(Iterable<ProductEntity> productEntity);
}
