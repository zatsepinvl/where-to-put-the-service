package com.effective.ecommerce.clean.product.adapter.persistence;

import com.effective.ecommerce.clean.product.entity.Product;
import com.effective.ecommerce.clean.product.usecase.port.out.ReadProductOutPort;
import com.effective.ecommerce.clean.product.usecase.port.out.SaveProductOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements SaveProductOutPort, ReadProductOutPort {
    private final ProductRepository productRepository;
    private final ProductEntityMapper entityMapper;

    @Override
    public Product save(Product product) {
        var entity = entityMapper.fromProduct(product);
        if (product.id() == null) {
            entity.setCreatedAt(ZonedDateTime.now());
        }
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
