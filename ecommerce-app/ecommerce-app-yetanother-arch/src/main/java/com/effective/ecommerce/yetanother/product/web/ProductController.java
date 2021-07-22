package com.effective.ecommerce.yetanother.product.web;

import com.effective.ecommerce.yetanother.product.domain.api.Product;
import com.effective.ecommerce.yetanother.product.domain.api.ReadProductService;
import com.effective.ecommerce.yetanother.product.domain.api.WriteProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ReadProductService readProductService;
    private final WriteProductService writeProductService;
    private final ProductRequestMapper requestMapper;

    @GetMapping
    public Iterable<Product> getProducts() {
        return readProductService.getAllProducts();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Product createProduct(@Valid @RequestBody SaveProductRequest request) {
        var command = requestMapper.toCreateProductCommand(request);
        return writeProductService.createProduct(command);
    }

    @GetMapping("/{productId}")
    public Product createProduct(@PathVariable long productId) {
        return readProductService.getProduct(productId);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@Valid @RequestBody SaveProductRequest request, @PathVariable long productId) {
        request.setProductId(productId);
        var command = requestMapper.toUpdateProductCommand(request);
        return writeProductService.updateProduct(command);
    }

}
