package org.example.sample.orders.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.vertx.mutiny.sqlclient.Row;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ORDER", schema = "public")
@Cacheable
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends PanacheEntityBase {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "USERNAME")
    private String username;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderDetail> orderDetails;

    public static Order fromRow(Row row) {
        return Order.builder()
                .id(row.getLong("id"))
                .quantity(row.getBigDecimal("quantity"))
                .createdDate(row.getLocalDateTime("createdDate"))
                .build()
                ;
    }
}
