package com.effective.ecommerce.clean.product.adapter.persistence.impl;

import com.effective.ecommerce.clean.product.adapter.persistence.jpa.ProductEntity;
import com.effective.ecommerce.clean.product.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

    ProductEntity fromProduct(Product product);

    Product toProduct(ProductEntity productEntity);

    Iterable<Product> toProducts(Iterable<ProductEntity> productEntity);
}
