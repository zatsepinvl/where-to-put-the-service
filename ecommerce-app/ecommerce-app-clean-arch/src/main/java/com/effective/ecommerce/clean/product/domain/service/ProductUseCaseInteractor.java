package com.effective.ecommerce.clean.product.domain.service;

import com.effective.ecommerce.clean.product.model.Product;
import com.effective.ecommerce.clean.product.domain.port.in.CreateProductCommand;
import com.effective.ecommerce.clean.product.domain.port.in.ReadProductUseCase;
import com.effective.ecommerce.clean.product.domain.port.in.UpdateProductCommand;
import com.effective.ecommerce.clean.product.domain.port.in.WriteProductUseCase;
import com.effective.ecommerce.clean.product.domain.port.out.ReadProductOutPort;
import com.effective.ecommerce.clean.product.domain.port.out.SaveProductOutPort;
import com.effective.ecommerce.hexagonal.exception.application.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class ProductUseCaseInteractor implements ReadProductUseCase, WriteProductUseCase {

    private final ReadProductOutPort readProductOutPort;
    private final SaveProductOutPort createProductOutPort;
    private final SaveProductCommandMapper createProductCommandMapper;

    @Override
    public Iterable<Product> getAllProducts() {
        return readProductOutPort.getAllProducts();
    }

    @Override
    public Product getProduct(long productId) {
        return readProductOutPort.getProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
    }

    @Override
    public boolean doesProductExist(long productId) {
        return readProductOutPort.exists(productId);
    }


    @Override
    public Product createProduct(CreateProductCommand command) {
        var product = createProductCommandMapper.commandToProduct(command);
        return createProductOutPort.save(product);
    }

    @Override
    public Product updateProduct(UpdateProductCommand command) {
        var product = createProductCommandMapper.commandToProduct(command);
        return createProductOutPort.save(product);
    }
}
