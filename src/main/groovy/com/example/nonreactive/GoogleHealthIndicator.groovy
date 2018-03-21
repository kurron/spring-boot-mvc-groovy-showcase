package com.example.nonreactive

import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestOperations

/**
 * A custom health indicator that tracks if Google is available or not.
 */
class GoogleHealthIndicator implements HealthIndicator {

    private final RestOperations template

    GoogleHealthIndicator( RestOperations aTemplate ) {
        template = aTemplate
    }

    @Override
    Health health() {
        def start = System.currentTimeMillis()
        ResponseEntity<String> response = template.getForEntity( 'https://www.google.com/', String )
        def duration = System.currentTimeMillis() - start
        def status = HttpStatus.OK == response.statusCode ? Health.up() : Health.down()
        status.withDetail( 'latency (ms)', duration ).build()
    }
}
