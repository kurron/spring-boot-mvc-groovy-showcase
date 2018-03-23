package com.example.nonreactive

import com.example.nonreactive.service.one.outbound.ContactRepository
import com.example.nonreactive.service.one.outbound.SaveItGateway
import com.example.nonreactive.service.one.outbound.UserRepository
import com.example.nonreactive.service.one.outbound.VehicleRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Configuration for accessing the downstream persistence stores, such as MongoDB or Redis.
 */
@Configuration
class PersistenceConfiguration {

    @Bean
    SaveItGateway SaveItGateway( UserRepository relational, VehicleRepository document, ContactRepository keyValue ) {
        new SaveItGateway( relational, document, keyValue )
    }

}
