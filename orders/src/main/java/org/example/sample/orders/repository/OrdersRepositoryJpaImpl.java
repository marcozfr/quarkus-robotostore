package org.example.sample.orders.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.smallrye.mutiny.Multi;
import org.example.sample.config.qualifiers.JpaReactive;
import org.example.sample.orders.model.Order;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@JpaReactive
public class OrdersRepositoryJpaImpl implements OrdersRepository, PanacheRepository<Order> {

    @Override
    public Multi<Order> findAllOrders() {
        return Multi.createFrom().iterable(findAll().list());
    }
}
