package com.example.nonreactive

import com.example.nonreactive.service.one.InboundGateway
import com.example.nonreactive.shared.ApplicationProperties
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

    @Bean
    InboundGateway inboundGateway( ApplicationProperties configuration ) {
        new InboundGateway( configuration )
    }
}
