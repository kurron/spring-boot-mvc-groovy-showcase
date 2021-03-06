package com.example.nonreactive

import com.example.nonreactive.service.one.core.Processor
import com.example.nonreactive.service.one.inbound.CustomExceptionHandler
import com.example.nonreactive.service.one.inbound.InboundGateway
import com.example.nonreactive.shared.ApplicationProperties
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Configuration for the web aspects of inbound HTTP services.
 */
@Configuration
class InboundHttpConfiguration {

    @Autowired
    ApplicationProperties configuration

    @Autowired
    MeterRegistry registry

    @Bean
    InboundGateway inboundGateway( ApplicationProperties configuration, MeterRegistry registry, Processor processor ) {
        new InboundGateway( configuration, registry, processor )
    }

    @Bean
    CustomExceptionHandler customExceptionHandler() {
        new CustomExceptionHandler()
    }
}
