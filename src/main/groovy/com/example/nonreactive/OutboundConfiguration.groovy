package com.example.nonreactive

import com.example.nonreactive.service.one.outbound.ContactRepository
import com.example.nonreactive.service.one.outbound.CustomClientHttpRequestInterceptor
import com.example.nonreactive.service.one.outbound.OutboundGateway
import com.example.nonreactive.service.one.outbound.SaveItGateway
import com.example.nonreactive.service.one.outbound.UserRepository
import com.example.nonreactive.service.one.outbound.VehicleRepository
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

/**
 * Configuration for accessing the downstream services, such as Google or GitHub.
 */
@Configuration
class OutboundConfiguration {

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

    @Bean
    SaveItGateway SaveItGateway( UserRepository relational, VehicleRepository document, ContactRepository keyValue ) {
        new SaveItGateway( relational, document, keyValue )
    }
}
