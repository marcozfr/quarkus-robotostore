package org.example.sample.products.client;

import com.mongodb.BasicDBObject;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.example.sample.products.dto.ProductDTO;
import org.example.sample.products.repository.ProductsRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Slf4j
public class ProductsListener {

    @Inject
    ProductsRepository productsRepository;

    @Incoming("products")
    @Transactional
    public void consume(ProductDTO productDTO) {
        log.info("Received product : {}", productDTO);
        productsRepository.mongoCollection().updateMany(
                new BasicDBObject("_id", new ObjectId(productDTO.getId())),
                new BasicDBObject("$inc", new BasicDBObject("stock", -productDTO.getStock().doubleValue()))
        ).subscribe().with(c -> {
            log.info("Updated {} documents" , c);
        }, error -> {
            log.error("error , " , error);
        });

    }

}
