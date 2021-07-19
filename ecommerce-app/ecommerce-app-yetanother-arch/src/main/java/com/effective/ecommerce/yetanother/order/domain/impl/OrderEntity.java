package com.effective.ecommerce.yetanother.order.domain.impl;

import com.effective.ecommerce.yetanother.order.domain.api.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
//Although be careful with Lombok and JPA: https://dzone.com/articles/lombok-and-jpa-what-may-go-wrong
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class OrderEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private ZonedDateTime createdAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany
    @JoinColumn(name = "pk_order_id")
    private List<OrderItemEntity> orderItems;
}
