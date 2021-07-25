package com.effective.ecommerce.clean.product.domain.service;

import com.effective.ecommerce.clean.product.model.Product;
import com.effective.ecommerce.clean.product.domain.port.in.CreateProductCommand;
import com.effective.ecommerce.clean.product.domain.port.in.UpdateProductCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaveProductCommandMapper {

    Product commandToProduct(CreateProductCommand command);

    Product commandToProduct(UpdateProductCommand command);
}
