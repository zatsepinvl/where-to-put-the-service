package com.effective.ecommerce.yetanother.product.web;

import com.effective.ecommerce.yetanother.product.domain.api.Product;
import com.effective.ecommerce.yetanother.product.domain.api.ReadProductService;
import com.effective.ecommerce.yetanother.product.domain.api.WriteProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ReadProductService readProductService;
    private final WriteProductService writeProductService;
    private final ProductRequestMapper requestMapper;

    @GetMapping
    public @NotNull Iterable<Product> getProducts() {
        return readProductService.getAllProducts();
    }

    @PostMapping
    public @NotNull Product createProduct(@Valid SaveProductRequest request) {
        var command = requestMapper.toCreateProductCommand(request);
        return writeProductService.createProduct(command);
    }

    @PutMapping("/{productId}")
    public @NotNull Product updateProduct(@RequestBody @Valid SaveProductRequest request, @PathVariable long productId) {
        request.setProductId(productId);
        var command = requestMapper.toUpdateProductCommand(request);
        return writeProductService.updateProduct(command);
    }

}
