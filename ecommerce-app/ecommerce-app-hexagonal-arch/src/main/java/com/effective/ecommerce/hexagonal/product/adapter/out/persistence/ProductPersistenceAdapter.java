package com.effective.ecommerce.hexagonal.product.adapter.out.persistence;

import com.effective.ecommerce.hexagonal.product.application.port.out.SaveProductOutPort;
import com.effective.ecommerce.hexagonal.product.application.port.out.ReadProductOutPort;
import com.effective.ecommerce.hexagonal.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements SaveProductOutPort, ReadProductOutPort {
    private final ProductRepository productRepository;
    private final ProductEntityMapper entityMapper;

    @Override
    public Product save(Product product) {
        var entity = entityMapper.fromProduct(product);
        entity = productRepository.save(entity);
        return entityMapper.toProduct(entity);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        var entities = productRepository.findAll();
        return entityMapper.toProducts(entities);
    }

    @Override
    public Optional<Product> getProductById(long productId) {
        return productRepository.findById(productId)
                .map(entityMapper::toProduct);
    }

    @Override
    public boolean exists(long productId) {
        return productRepository.existsById(productId);
    }
}
