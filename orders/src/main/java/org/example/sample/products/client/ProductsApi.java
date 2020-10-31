package org.example.sample.products.client;

import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.sample.products.dto.ProductDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

@RegisterRestClient
@Path("/products")
public interface ProductsApi {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Asynchronous
    CompletionStage<ProductDTO> getProduct(@PathParam("id") String id);

}
