package com.effective.ecommerce.yetanother.product.domain.impl;

import com.effective.ecommerce.yetanother.product.domain.api.Product;
import com.effective.ecommerce.yetanother.product.domain.api.command.CreateProductCommand;
import com.effective.ecommerce.yetanother.product.domain.api.command.UpdateProductCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface ProductEntityMapper {

    ProductEntity fromProductCreateCommand(CreateProductCommand createProductCommand);

    ProductEntity fromProductUpdateCommand(UpdateProductCommand updateCommand);

    Product toProduct(ProductEntity productEntity);

    Iterable<Product> toProducts(Iterable<ProductEntity> productEntity);

}
