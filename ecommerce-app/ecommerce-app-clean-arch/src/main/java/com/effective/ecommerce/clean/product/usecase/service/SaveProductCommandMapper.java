package com.effective.ecommerce.clean.product.usecase.service;

import com.effective.ecommerce.clean.product.model.Product;
import com.effective.ecommerce.clean.product.usecase.port.in.CreateProductCommand;
import com.effective.ecommerce.clean.product.usecase.port.in.UpdateProductCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaveProductCommandMapper {

    Product commandToProduct(CreateProductCommand command);

    Product commandToProduct(UpdateProductCommand command);
}
