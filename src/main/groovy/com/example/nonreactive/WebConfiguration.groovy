package com.example.nonreactive

import com.example.nonreactive.service.one.inbound.InboundGateway
import com.example.nonreactive.shared.ApplicationProperties
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Configuration for the web aspects of the service.
 */
@Configuration
class WebConfiguration {

    @Autowired
    ApplicationProperties configuration

    @Autowired
    MeterRegistry registry

    @Bean
    InboundGateway inboundGateway( ApplicationProperties configuration, MeterRegistry registry ) {
        new InboundGateway( configuration, registry )
    }
}
