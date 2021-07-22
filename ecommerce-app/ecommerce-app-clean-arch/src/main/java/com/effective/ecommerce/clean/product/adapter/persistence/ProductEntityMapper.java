package com.effective.ecommerce.clean.product.adapter.persistence;

import com.effective.ecommerce.clean.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

    ProductEntity fromProduct(Product product);

    Product toProduct(ProductEntity productEntity);

    Iterable<Product> toProducts(Iterable<ProductEntity> productEntity);
}
