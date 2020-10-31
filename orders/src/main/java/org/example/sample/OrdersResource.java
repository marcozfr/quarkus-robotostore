package org.example.sample;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.sample.common.Utils;
import org.example.sample.config.qualifiers.JpaReactive;
import org.example.sample.config.qualifiers.PgReactive;
import org.example.sample.orders.dto.OrderDTO;
import org.example.sample.orders.model.Order;
import org.example.sample.orders.model.OrderDetail;
import org.example.sample.orders.repository.OrdersRepository;
import org.example.sample.products.client.ProductsApi;
import org.example.sample.products.dto.ProductDTO;
import org.example.sample.users.client.UsersApi;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/orders")
@Slf4j
public class OrdersResource {

    @Inject
    Config config;

    @Inject
    @RestClient
    UsersApi usersApi;

    @Inject
    @RestClient
    ProductsApi productsApi;

    @Inject
    @PgReactive
    OrdersRepository ordersRepositoryPgClient;

    @Inject
    @JpaReactive
    OrdersRepository ordersRepositoryJpa;

    @Inject
    @Channel("products")
    Emitter<ProductDTO> productDTOEmitter;

    @GET
    @Produces({MediaType.APPLICATION_JSON, "application/stream+json"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Multi<OrderDTO> getOrders(){
        return ordersRepositoryJpa.findAllOrders()
                .onItem()
                .transform(Utils::toOrderDTO);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Uni<OrderDTO> createOrder(OrderDTO orderRequest){

        Order order = Utils.toOrder(orderRequest);

        Uni<Order> comfirmUserOnOrder = Uni.createFrom()
                .completionStage(usersApi.getClient(order.getUsername()))
                .onItem()
                .transform(user -> {
                    order.setUsername(user.getUsername());
                    return order;
                });

        Uni<List<OrderDetail>> checkStockOnProducts = Multi.createFrom()
                .iterable(order.getOrderDetails())
                .onItem()
                .transformToUni(orderDetail -> validateOrderDetail(orderDetail))
                .merge()
                .collectItems()
                .asList();

        return Uni.combine().all()
                .unis(comfirmUserOnOrder, checkStockOnProducts)
                .asTuple()
                .onItem()
                .transform(tuple -> tuple.getItem1())
                .onItem()
                .transform( o -> {
                    log.info(" resulting order is {}", o);
                    return saveOrder(o);
                })
                .onItem()
                .transform(Utils::toOrderDTO);
    }

    private Order saveOrder(Order order) {
        order.persistAndFlush();
        order.getOrderDetails().forEach(orderDetail -> {
            ProductDTO product = Utils.toProductDTO(orderDetail);
            log.info("sending order detail to kafka : {} ", product);
            productDTOEmitter.send(product);
        });
        return order;
    }

    private Uni<OrderDetail> validateOrderDetail(OrderDetail orderDetail) {
        return Uni.createFrom()
                .completionStage(productsApi.getProduct(orderDetail.getProductId()))
                .onItem()
                .transform(product -> {
                    if(orderDetail.getQuantity().compareTo(product.getStock()) > 0){
                        throw new WebApplicationException(Response
                                .status(Response.Status.BAD_REQUEST)
                                .entity("El producto no cuenta con stock disponible")
                                .build());
                    }
                    return orderDetail;
                });
    }


}
