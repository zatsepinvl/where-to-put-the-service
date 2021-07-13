package com.effective.ecommerce.hexagonal.product.application.service;

import com.effective.ecommerce.hexagonal.exception.application.ResourceNotFoundException;
import com.effective.ecommerce.hexagonal.product.application.port.in.SaveProductCommand;
import com.effective.ecommerce.hexagonal.product.application.port.in.SaveProductUseCase;
import com.effective.ecommerce.hexagonal.product.application.port.in.ReadProductUseCase;
import com.effective.ecommerce.hexagonal.product.application.port.out.SaveProductOutPort;
import com.effective.ecommerce.hexagonal.product.application.port.out.ReadProductOutPort;
import com.effective.ecommerce.hexagonal.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class ProductServiceImpl implements ReadProductUseCase, SaveProductUseCase {

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
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id "+productId));
    }

    @Override
    public boolean doesProductExist(long productId) {
        return readProductOutPort.exists(productId);
    }

    @Override
    public Product save(SaveProductCommand command) {
        var product = createProductCommandMapper.commandToProduct(command);
        return createProductOutPort.save(product);
    }
}
