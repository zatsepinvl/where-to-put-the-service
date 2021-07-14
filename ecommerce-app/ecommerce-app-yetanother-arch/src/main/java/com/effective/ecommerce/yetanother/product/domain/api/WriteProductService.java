package com.effective.ecommerce.yetanother.product.domain.api;

import com.effective.ecommerce.yetanother.product.domain.api.command.CreateProductCommand;
import com.effective.ecommerce.yetanother.product.domain.api.command.UpdateProductCommand;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface WriteProductService {

    Product createProduct(@NotNull @Valid CreateProductCommand createCommand);

    Product updateProduct(@NotNull @Valid UpdateProductCommand updateCommand);
}
