package org.example.sample.orders.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private Long orderId;
    private String username;
    private BigDecimal quantity;
    private LocalDateTime createdDate;
    @NotNull
    private List<OrderDetailDTO> orderDetails;

}
