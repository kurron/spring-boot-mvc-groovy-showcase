package com.example.nonreactive.service.one.core

import com.example.nonreactive.service.one.outbound.UserEntity
import com.example.nonreactive.service.one.outbound.UserRepository
import com.example.nonreactive.service.one.shared.UserModel
import com.example.nonreactive.service.one.shared.UserPort

/**
 * Core component that holds key business logic, leaving the integration plumbing
 * to the gateways. Production implementation of the {@link Processor} interface.
 */
class ProductionProcessor implements Processor {

    /**
     * Downstream stream service that assists the processor.
     */
    private final UserPort downstream

    /**
     * Downstream service that handles persistence.
     */
    private final UserRepository database

    ProductionProcessor( UserPort aDownstream, UserRepository aDatabase ) {
        downstream = aDownstream
        database = aDatabase
    }

    @Override
    UserModel loadUserData( String userID ) {
        // another option would be to provide a default object instead of throwing an error
        def model = downstream.fetchUser( userID ).orElseThrow( { new IllegalStateException( "No such user: ${userID}" )  } )

        // this is just show we can write to a relational database
        def entity = new UserEntity( username: model.username )
        database.save( entity )

        model
    }
}
