package org.example.sample.orders.repository;

import io.smallrye.mutiny.Multi;
import org.example.sample.orders.model.Order;

public interface OrdersRepository {

    Multi<Order> findAllOrders();

}
