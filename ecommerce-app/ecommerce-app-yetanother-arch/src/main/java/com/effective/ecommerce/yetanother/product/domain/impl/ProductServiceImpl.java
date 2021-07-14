package com.effective.ecommerce.yetanother.product.domain.impl;

import com.effective.ecommerce.yetanother.exception.application.ResourceNotFoundException;
import com.effective.ecommerce.yetanother.product.domain.api.Product;
import com.effective.ecommerce.yetanother.product.domain.api.ReadProductService;
import com.effective.ecommerce.yetanother.product.domain.api.command.CreateProductCommand;
import com.effective.ecommerce.yetanother.product.domain.api.WriteProductService;
import com.effective.ecommerce.yetanother.product.domain.api.command.UpdateProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class ProductServiceImpl implements ReadProductService, WriteProductService {

    private final ProductRepository productRepository;
    private final ProductEntityMapper entityMapper;

    @Override
    public Iterable<Product> getAllProducts() {
        var entities = productRepository.findAll();
        return entityMapper.toProducts(entities);
    }

    @Override
    public Product getProduct(long productId) {
        return productRepository.findById(productId)
                .map(entityMapper::toProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
    }

    @Override
    public boolean doesProductExist(long productId) {
        return productRepository.existsById(productId);
    }

    @Override
    public Product createProduct(CreateProductCommand createCommand) {
        var entity = entityMapper.fromProductCreateCommand(createCommand);
        entity = productRepository.save(entity);
        return entityMapper.toProduct(entity);
    }

    @Override
    public Product updateProduct(UpdateProductCommand updateCommand) {
        var productId = updateCommand.productId();
        if (!doesProductExist(productId)) {
            throw new ResourceNotFoundException("Product not found with id " + productId);
        }
        var entity = entityMapper.fromProductUpdateCommand(updateCommand);
        entity = productRepository.save(entity);
        return entityMapper.toProduct(entity);
    }

}
