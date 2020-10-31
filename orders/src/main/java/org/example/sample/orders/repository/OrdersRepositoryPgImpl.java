package org.example.sample.orders.repository;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import lombok.extern.slf4j.Slf4j;
import org.example.sample.config.qualifiers.PgReactive;
import org.example.sample.orders.model.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Slf4j
@PgReactive
public class OrdersRepositoryPgImpl implements OrdersRepository {

    @Inject
    PgPool pgReactiveClient;

    @Override
    public Multi<Order> findAllOrders() {
        return pgReactiveClient.query("select id, quantity, created_date from public.order")
                .execute()
                .onItem()
                .transformToMulti(rowset -> Multi.createFrom().iterable(rowset))
                .onItem()
                .transform(Order::fromRow)
                .onFailure()
                .invoke(e -> {
                    log.error("Error on findAll, exception is " , e);
                });
    }
}
