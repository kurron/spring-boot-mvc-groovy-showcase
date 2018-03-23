package com.example.nonreactive

import com.example.nonreactive.service.one.core.ProductionProcessor
import com.example.nonreactive.service.one.outbound.SaveItPort
import com.example.nonreactive.service.one.shared.UserPort
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Timer as MicrometerTimer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestOperations

/**
 * Configuration for beans that should be generally available to all "slices".
 */
@Configuration
class GeneralConfiguration {

    @Bean
    CustomInfoContributor customInfoContributor() {
        new CustomInfoContributor()
    }

    @Bean
    ProductionProcessor productionProcessor( UserPort userPort,
                                             SaveItPort saveItPort,
                                             MicrometerTimer serviceTimer ) {
        new ProductionProcessor( userPort, saveItPort, serviceTimer )
    }

    @Bean
    CustomActuator customActuator() {
        new CustomActuator()
    }

    @Bean
    GoogleHealthIndicator googleHealthIndicator( RestOperations template ) {
        new GoogleHealthIndicator( template )
    }

    @Bean
    MicrometerTimer serviceTimer( MeterRegistry registry ) {
        registry.timer( 'service-timer', 'layer','core' )
    }
}
