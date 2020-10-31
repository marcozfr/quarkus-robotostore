package org.example.sample.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
//@Liveness
@Readiness
public class ProductsResourceHealthCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.builder()
                .name("PRODUCTS_API")
                .down()
                .withData("auth_status", "false")
                .build();
    }
}
