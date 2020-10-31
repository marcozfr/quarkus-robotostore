package org.example.sample.products.dto;

import lombok.*;

import javax.persistence.Cacheable;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ProductDTO {

    private String id;
    private BigDecimal stock;

}
