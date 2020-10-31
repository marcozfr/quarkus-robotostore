package org.example.sample.users.provider;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import java.io.*;

@Slf4j
public class ClientApiRequestFilter implements ClientRequestFilter, ClientResponseFilter {

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        log.info("Intercepting user API request, URL is: {} ",
                requestContext.getUri().toString());
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        log.info("Intercepting user API response, Status is: {} {}",
                responseContext.getStatus(), responseContext.getStatusInfo());
    }
}
