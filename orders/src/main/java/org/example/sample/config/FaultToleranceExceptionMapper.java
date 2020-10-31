package org.example.sample.config;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.exceptions.CircuitBreakerOpenException;
import org.eclipse.microprofile.faulttolerance.exceptions.FaultToleranceException;
import org.eclipse.microprofile.faulttolerance.exceptions.TimeoutException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Slf4j
public class FaultToleranceExceptionMapper implements ExceptionMapper<FaultToleranceException> {
    @Override
    public Response toResponse(FaultToleranceException exception) {
        log.error("exception found " , exception);
        if(exception instanceof CircuitBreakerOpenException){
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity("{\"message\": \"<Circuit Breaker>. Estamos teniendo problemas " +
                            "con nuestro servicio. Por favor intente mas tarde.\"}")
                    .build();
        }else if(exception instanceof TimeoutException){
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity("{\"message\": \"<Timeout>. Estamos teniendo problemas " +
                            "con nuestro servicio. Por favor intente mas tarde.\"}")
                    .build();
        }
        return null;
    }
}
