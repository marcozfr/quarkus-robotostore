package org.example.sample;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.example.sample.products.model.Product;
import org.example.sample.products.repository.ProductsRepository;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/products")
@Slf4j
public class ProductsResource {

    @Inject
    ProductsRepository productsRepository;

    @Inject
    @Channel("products")
    Publisher<Product> productsListener;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Multi<Product> getProducts(){
        return productsRepository
                .findAll()
                .stream();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Product> createProduct(Product product){
        return productsRepository
                .persist(product)
                .map(v -> product)
                .onFailure()
                .invoke(e -> log.error("Error on product creation , ", e))
                ;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> getProduct(@PathParam("id") String id) {
        return productsRepository
                .findById(new ObjectId(id))
                .onItem()
                .transform(p -> p!=null ? Response.ok(p) : Response.status(Response.Status.NOT_FOUND))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

}