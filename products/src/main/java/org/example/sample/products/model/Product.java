package org.example.sample.products.model;

import io.quarkus.mongodb.panache.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@MongoEntity(collection = "product")
@Getter
@Setter
@ToString
public class Product {

    private ObjectId id;
    private String description;
    private BigDecimal originalPrice;
    private BigDecimal discountedPrice;
    private Long stock;

}
