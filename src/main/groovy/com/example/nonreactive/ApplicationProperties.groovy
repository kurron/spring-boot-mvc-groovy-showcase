package com.example.nonreactive

import groovy.transform.Canonical
import javax.validation.Valid
import javax.validation.constraints.NotNull
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

/**
 * Type-safe configuration properties for the application.
 */
@Canonical
@ConfigurationProperties( prefix = 'com.example' )
@Validated
class ApplicationProperties {

    /**
     * Unique id of the running instance.
     */
    @NotNull
    UUID instance

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
