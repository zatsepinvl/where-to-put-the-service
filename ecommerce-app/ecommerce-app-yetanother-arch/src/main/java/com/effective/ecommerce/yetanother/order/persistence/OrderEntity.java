package com.effective.ecommerce.yetanother.order.persistence;

import com.effective.ecommerce.yetanother.order.domain.api.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private OrderStatus status;

    @OneToMany
    @JoinColumn(name = "pk.pk_order_id")
    private List<OrderItemEntity> orderItems;
}
