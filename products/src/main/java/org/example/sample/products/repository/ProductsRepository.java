package org.example.sample.products.repository;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import org.example.sample.products.model.Product;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductsRepository implements ReactivePanacheMongoRepository<Product> {

}
