package com.example.nonreactive

import com.example.nonreactive.service.one.outbound.CustomClientHttpRequestInterceptor
import com.example.nonreactive.service.one.outbound.OutboundGateway
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

/**
 * Configuration for accessing the downstream HTTP-based services, such as Google or GitHub.
 */
@Configuration
class OutboundHttpConfiguration {

    @Bean
    CustomClientHttpRequestInterceptor customClientHttpRequestInterceptor() {
        new CustomClientHttpRequestInterceptor()
    }

    @Bean
    OutboundGateway outboundGateway( RestTemplate restTemplate ) {
        new OutboundGateway( restTemplate )
    }

    @Bean
    RestTemplate restTemplate( RestTemplateBuilder builder, CustomClientHttpRequestInterceptor interceptor ) {
        builder.additionalInterceptors( interceptor ).build()
    }
}
