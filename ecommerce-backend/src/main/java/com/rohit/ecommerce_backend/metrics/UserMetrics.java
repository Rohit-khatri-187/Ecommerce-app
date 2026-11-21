package com.rohit.ecommerce_backend.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class UserMetrics {

    private final Counter registeredUsersCounter;

    public UserMetrics(MeterRegistry meterRegistry) {

        this.registeredUsersCounter = Counter.builder("users.registered")
                .description("Total number of successfully registered users")
                .register(meterRegistry);
    }

    public void incrementRegisteredUsers() {
        registeredUsersCounter.increment();
    }
}
