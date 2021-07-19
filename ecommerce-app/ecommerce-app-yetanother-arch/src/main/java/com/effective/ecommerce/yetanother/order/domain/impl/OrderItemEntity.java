package com.effective.ecommerce.yetanother.order.domain.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
class OrderItemEntity {

    @EmbeddedId
    @JsonIgnore
    private OrderItemPK pk;

    @Column(nullable = false)
    private Integer quantity;
}
