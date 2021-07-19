package com.effective.ecommerce.yetanother.product.web;

import com.effective.ecommerce.yetanother.product.domain.api.command.CreateProductCommand;
import com.effective.ecommerce.yetanother.product.domain.api.command.UpdateProductCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface ProductRequestMapper {
    CreateProductCommand toCreateProductCommand(SaveProductRequest request);

    UpdateProductCommand toUpdateProductCommand(SaveProductRequest request);
}
