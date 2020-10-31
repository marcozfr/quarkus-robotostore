package org.example.sample.orders.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailDTO {

    private String productId;
    private String productDescription;
    private BigDecimal quantity;

}
