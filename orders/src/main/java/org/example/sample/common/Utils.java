package org.example.sample.common;

import org.example.sample.orders.dto.OrderDTO;
import org.example.sample.orders.dto.OrderDetailDTO;
import org.example.sample.orders.model.Order;
import org.example.sample.orders.model.OrderDetail;
import org.example.sample.products.dto.ProductDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static OrderDTO toOrderDTO(Order order) {
        return OrderDTO.builder()
                .username(order.getUsername())
                .orderId(order.getId())
                .createdDate(order.getCreatedDate())
                .quantity(order.getQuantity())
                .orderDetails(order.getOrderDetails().stream()
                        .map(detail -> OrderDetailDTO.builder()
                                .productDescription(detail.getProductDescription())
                                .productId(detail.getProductId())
                                .quantity(detail.getQuantity())
                                .build()
                        ).collect(Collectors.toList())
                )
                .build()
                ;
    }

    public static Order toOrder(OrderDTO orderDTO) {
        Order order = Order.builder()
                .username(orderDTO.getUsername())
                .createdDate(LocalDateTime.now())
                .quantity(orderDTO.getOrderDetails().stream()
                        .map(x -> x.getQuantity())
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
                .build();

        List<OrderDetail> orderDetails = orderDTO
                .getOrderDetails()
                .stream()
                .map(orderDetail -> OrderDetail.builder()
                        .order(order)
                        .productId(orderDetail.getProductId())
                        .quantity(orderDetail.getQuantity())
                        .build()
                ).collect(Collectors.toList());

        order.setOrderDetails(orderDetails);

        return order;
    }

    public static ProductDTO toProductDTO(OrderDetail orderDetail) {
        return ProductDTO.builder()
                .stock(orderDetail.getQuantity())
                .id(orderDetail.getProductId())
                .build();
    }
}
