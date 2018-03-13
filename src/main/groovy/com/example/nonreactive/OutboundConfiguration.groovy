package com.example.nonreactive

import com.example.nonreactive.service.one.OutboundGateway
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Configuration for accessing the downstream services, such as Google or GitHub.
 */
@Configuration
class OutboundConfiguration {

    @Bean
    OutboundGateway outboundGateway( RestTemplateBuilder builder ) {
        new OutboundGateway( builder )
    }
}
