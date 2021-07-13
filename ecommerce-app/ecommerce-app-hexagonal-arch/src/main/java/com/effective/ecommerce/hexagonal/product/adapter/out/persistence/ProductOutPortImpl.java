package com.effective.ecommerce.hexagonal.product.adapter.out.persistence;

import com.effective.ecommerce.hexagonal.product.application.port.out.SaveProductOutPort;
import com.effective.ecommerce.hexagonal.product.application.port.out.ReadProductOutPort;
import com.effective.ecommerce.hexagonal.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class ProductOutPortImpl implements SaveProductOutPort, ReadProductOutPort {
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
        var products = productRepository.findAll();
        return StreamSupport.stream(products.spliterator(), false)
                .map(entityMapper::toProduct)
                .toList();
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
