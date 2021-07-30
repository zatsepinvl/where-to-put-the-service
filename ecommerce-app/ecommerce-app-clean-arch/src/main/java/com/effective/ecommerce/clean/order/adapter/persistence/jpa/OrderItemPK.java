package com.effective.ecommerce.clean.order.adapter.persistence.jpa;

import com.effective.ecommerce.clean.product.adapter.persistence.jpa.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
//Although be careful with Lombok and JPA: https://dzone.com/articles/lombok-and-jpa-what-may-go-wrong
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemPK implements Serializable {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pk_order_id")
    private OrderEntity order;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pk_product_id")
    private ProductEntity product;

}
