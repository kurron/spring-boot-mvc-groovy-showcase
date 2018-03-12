package com.example.nonreactive

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
}
