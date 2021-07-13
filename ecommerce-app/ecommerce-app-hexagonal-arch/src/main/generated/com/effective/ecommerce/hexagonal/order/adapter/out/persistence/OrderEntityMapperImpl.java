package com.effective.ecommerce.hexagonal.order.adapter.out.persistence;

import com.effective.ecommerce.hexagonal.order.domain.Order;
import com.effective.ecommerce.hexagonal.order.domain.OrderStatus;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-14T01:10:06+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class OrderEntityMapperImpl implements OrderEntityMapper {

    @Override
    public OrderEntity fromOrder(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setId( order.id() );
        orderEntity.setDateCreated( order.dateCreated() );
        orderEntity.setStatus( order.status() );

        return orderEntity;
    }

    @Override
    public Order toOrder(OrderEntity orderEntity) {
        if ( orderEntity == null ) {
            return null;
        }

        Long id = null;
        LocalDate dateCreated = null;
        OrderStatus status = null;

        id = orderEntity.getId();
        dateCreated = orderEntity.getDateCreated();
        status = orderEntity.getStatus();

        Order order = new Order( id, dateCreated, status );

        return order;
    }
}
