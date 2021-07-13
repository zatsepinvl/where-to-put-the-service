package com.effective.ecommerce.hexagonal.product.adapter.out.persistence;

import com.effective.ecommerce.hexagonal.product.domain.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-14T01:10:06+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class ProductEntityMapperImpl implements ProductEntityMapper {

    @Override
    public ProductEntity fromProduct(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setId( product.id() );
        productEntity.setName( product.name() );
        if ( product.price() != null ) {
            productEntity.setPrice( product.price().doubleValue() );
        }
        productEntity.setPictureUrl( product.pictureUrl() );

        return productEntity;
    }

    @Override
    public Product toProduct(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        BigDecimal price = null;
        String pictureUrl = null;

        id = productEntity.getId();
        name = productEntity.getName();
        if ( productEntity.getPrice() != null ) {
            price = BigDecimal.valueOf( productEntity.getPrice() );
        }
        pictureUrl = productEntity.getPictureUrl();

        Product product = new Product( id, name, price, pictureUrl );

        return product;
    }

    @Override
    public Iterable<Product> fromProducts(Iterable<ProductEntity> productEntities) {
        if ( productEntities == null ) {
            return null;
        }

        ArrayList<Product> iterable = new ArrayList<Product>();
        for ( ProductEntity productEntity : productEntities ) {
            iterable.add( toProduct( productEntity ) );
        }

        return iterable;
    }
}
