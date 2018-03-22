package com.example.nonreactive

import com.example.nonreactive.service.one.core.ProductionProcessor
import com.example.nonreactive.service.one.outbound.ContactRepository
import com.example.nonreactive.service.one.outbound.UserRepository
import com.example.nonreactive.service.one.outbound.VehicleRepository
import com.example.nonreactive.service.one.shared.UserPort
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
    ProductionProcessor productionProcessor( UserPort port,
                                             UserRepository relational,
                                             VehicleRepository document,
                                             ContactRepository keyValue ) {
        new ProductionProcessor( port, relational, document, keyValue )
    }

    @Bean
    CustomActuator customActuator() {
        new CustomActuator()
    }

    @Bean
    GoogleHealthIndicator googleHealthIndicator( RestOperations template ) {
        new GoogleHealthIndicator( template )
    }
}
