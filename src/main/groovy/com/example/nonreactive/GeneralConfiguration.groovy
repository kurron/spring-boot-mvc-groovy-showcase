package com.example.nonreactive

import com.example.nonreactive.service.one.core.ProductionProcessor
import com.example.nonreactive.service.one.shared.UserPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

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
    ProductionProcessor productionProcessor( UserPort userPort ) {
        new ProductionProcessor( userPort )
    }
}
