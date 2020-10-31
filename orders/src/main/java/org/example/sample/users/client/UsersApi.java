package org.example.sample.users.client;

import org.eclipse.microprofile.faulttolerance.*;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.sample.users.provider.ExceptionMapper;
import org.example.sample.users.model.User;
import org.example.sample.users.provider.ClientApiRequestFilter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;


@RegisterRestClient(configKey = "users-api")
@Path("/users")
@RegisterProvider(ClientApiRequestFilter.class)
@RegisterProvider(ExceptionMapper.class)
public interface UsersApi {

    @GET
    @Path(value = "/{username}")
    @Produces(MediaType.APPLICATION_JSON)
//    @Retry(maxRetries = 3)
//    @Timeout(1000)
    @Asynchronous
    @CircuitBreaker(requestVolumeThreshold = 4)
//    @Fallback(DefaultFallBackHandler.class)
    CompletionStage<User> getClient(@PathParam("username") String username);

}
