package com.effective.ecommerce.clean.product.adapter.web;

import com.effective.ecommerce.clean.product.usecase.port.in.CreateProductCommand;
import com.effective.ecommerce.clean.product.usecase.port.in.UpdateProductCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {
    CreateProductCommand toCreateProductCommand(SaveProductRequest request);

    UpdateProductCommand toUpdateProductCommand(SaveProductRequest request);
}
