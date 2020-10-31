package org.example.sample.users.provider;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.exceptions.CircuitBreakerOpenException;
import org.eclipse.microprofile.faulttolerance.exceptions.FaultToleranceException;
import org.eclipse.microprofile.faulttolerance.exceptions.TimeoutException;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;
import org.jboss.resteasy.api.validation.ResteasyViolationException;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Slf4j
public class ExceptionMapper implements ResponseExceptionMapper<Throwable> {

    @Override
    public boolean handles(int status, MultivaluedMap headers) {
        return false;
    }

    @Override
    public Throwable toThrowable(Response response) {
        log.error(" Error , {} ",response.getEntity());
        return null;
    }

    @Override
    public int getPriority() {
        return 100;
    }
}
