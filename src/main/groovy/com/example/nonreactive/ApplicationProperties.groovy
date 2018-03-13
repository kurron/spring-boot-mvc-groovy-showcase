package com.example.nonreactive

import groovy.transform.Canonical
import java.time.Duration
import java.time.temporal.ChronoUnit
import javax.validation.Valid
import javax.validation.constraints.NotNull
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.convert.DurationUnit
import org.springframework.validation.annotation.Validated

/**
 * Type-safe configuration properties for the application.
 */
@Canonical
@ConfigurationProperties( prefix = 'service' )
@Validated
class ApplicationProperties {

    /**
     * Unique id of the running instance.
     */
    @NotNull
    UUID instance = UUID.randomUUID()

    /**
     * How long to wait, in seconds, for downstream services.
     */
    @NotNull
    @DurationUnit( ChronoUnit.SECONDS )
    Duration timeout = Duration.ofSeconds( 60 )

    @Valid
    final Nested nested = new Nested()

    static class Nested {

        /**
         * Location of a service.
         */
        @NotNull
        URL location
    }
}
