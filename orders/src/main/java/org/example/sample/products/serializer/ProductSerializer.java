package org.example.sample.products.serializer;

import io.quarkus.kafka.client.serialization.JsonbSerializer;
import org.example.sample.products.dto.ProductDTO;

public class ProductSerializer extends JsonbSerializer<ProductDTO> {

}
