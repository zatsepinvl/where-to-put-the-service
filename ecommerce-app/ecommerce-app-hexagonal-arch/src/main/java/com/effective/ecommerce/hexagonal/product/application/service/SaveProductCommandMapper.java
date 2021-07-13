package com.effective.ecommerce.hexagonal.product.application.service;

import com.effective.ecommerce.hexagonal.product.application.port.in.SaveProductCommand;
import com.effective.ecommerce.hexagonal.product.domain.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaveProductCommandMapper {

    Product commandToProduct(SaveProductCommand command);
}
