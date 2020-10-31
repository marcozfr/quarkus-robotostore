package org.example.sample;

import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.example.sample.users.model.User;
import org.example.sample.users.service.UsersService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Slf4j
public class UsersResource {

    @Inject
    UsersService usersService;

    @GET
    @Path(value = "/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(name="getUserHits",description = "Cuantas peticiones se han hecho a este servicio")
    @Timed(name="getUserTime",description = "Cuanto demoro en responder este servicio")
    public Uni<User> getUser(@PathParam("username") String username) {

//        Utils.mayThrowApplicationError();
//        Utils.willThrowApplicationError();
//        Utils.delayBy(5);
        return usersService
                .getUserByUsername(username)
                .onItem()
                .ifNull()
                .failWith(new WebApplicationException(
                        Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"message\" : \"no se encontro usuario \"}")
                        .build()
                ));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<User> createUser(User user){
        return usersService.createUser(user);
    }
}