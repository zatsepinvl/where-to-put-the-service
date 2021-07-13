package com.effective.ecommerce.hexagonal.product.application.service;

import com.effective.ecommerce.hexagonal.product.application.port.in.SaveProductCommand;
import com.effective.ecommerce.hexagonal.product.domain.Product;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-14T01:10:06+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class SaveProductCommandMapperImpl implements SaveProductCommandMapper {

    @Override
    public Product commandToProduct(SaveProductCommand command) {
        if ( command == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        BigDecimal price = null;
        String pictureUrl = null;

        id = command.id();
        name = command.name();
        price = command.price();
        pictureUrl = command.pictureUrl();

        Product product = new Product( id, name, price, pictureUrl );

        return product;
    }
}
