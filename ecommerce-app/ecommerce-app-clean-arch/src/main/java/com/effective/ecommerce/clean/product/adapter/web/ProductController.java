package com.effective.ecommerce.clean.product.adapter.web;

import com.effective.ecommerce.clean.product.usecase.port.in.ReadProductUseCase;
import com.effective.ecommerce.clean.product.entity.Product;
import com.effective.ecommerce.clean.product.usecase.port.in.WriteProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final WriteProductUseCase writeProductUseCase;
    private final ReadProductUseCase readProductUseCase;
    private final ProductRequestMapper requestMapper;

    @GetMapping
    public @NotNull Iterable<Product> getProducts() {
        return readProductUseCase.getAllProducts();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Product createProduct(@Valid @RequestBody SaveProductRequest request) {
        var command = requestMapper.toCreateProductCommand(request);
        return writeProductUseCase.createProduct(command);
    }

    @GetMapping("/{productId}")
    public Product createProduct(@PathVariable long productId) {
        return readProductUseCase.getProduct(productId);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@Valid @RequestBody SaveProductRequest request, @PathVariable long productId) {
        request.setProductId(productId);
        var command = requestMapper.toUpdateProductCommand(request);
        return writeProductUseCase.updateProduct(command);
    }
}
