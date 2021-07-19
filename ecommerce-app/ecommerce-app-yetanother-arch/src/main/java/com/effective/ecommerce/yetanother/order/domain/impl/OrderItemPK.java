package com.effective.ecommerce.yetanother.order.domain.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class OrderItemPK implements Serializable {

    @Column(name = "pk_order_id")
    private Long orderId;

    @Column(name = "pk_product_id")
    private Long productId;
}
