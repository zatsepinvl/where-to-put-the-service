package com.effective.ecommerce.clean.order.adapter.persistence.jpa;

import com.effective.ecommerce.clean.order.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private ZonedDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
