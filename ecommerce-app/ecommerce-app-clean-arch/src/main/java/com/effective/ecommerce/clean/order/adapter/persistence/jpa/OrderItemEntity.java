package com.effective.ecommerce.clean.order.adapter.persistence.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity {

    @EmbeddedId
    private OrderItemPK pk;

    @Column(nullable = false)
    private Integer quantity;
}
