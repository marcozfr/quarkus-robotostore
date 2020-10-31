package org.example.sample.products.serializer;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;
import org.example.sample.products.dto.ProductDTO;

import javax.json.bind.Jsonb;

public class ProductDeserializer extends JsonbDeserializer<ProductDTO> {

    public ProductDeserializer(){
        super(ProductDTO.class);
    }

    public ProductDeserializer(Class<ProductDTO> type) {
        super(type);
    }

    public ProductDeserializer(Class<ProductDTO> type, Jsonb jsonb) {
        super(type, jsonb);
    }
}
