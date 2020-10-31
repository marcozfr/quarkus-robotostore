package org.example.sample.orders.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ORDER_DETAIL", schema = "public")
@Cacheable
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail extends PanacheEntity {

    @Column(nullable = false, name = "PRODUCT_ID")
    private String productId;

    @Column(nullable = true, name = "PRODUCT_DESCRIPTION")
    private String productDescription;

    @Column(nullable = false, name = "QUANTITY")
    private BigDecimal quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;
}
