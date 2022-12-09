package com.snapface.api.actuator;

import com.snapface.api.model.Facesnap;
import com.snapface.api.service.FacesnapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

    @Autowired
    FacesnapService facesnapService;

    @Override
    public Health health() {

        if (! facesnapService.getFacesnap(1).isPresent()) {
            return Health.down().withDetail("Cause", "BDD is not available").build();
        }

        return Health.up().build();
    }
}
