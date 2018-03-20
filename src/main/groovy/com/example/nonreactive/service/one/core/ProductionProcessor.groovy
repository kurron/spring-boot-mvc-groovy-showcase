package com.example.nonreactive.service.one.core

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

    ProductionProcessor( UserPort aDownstream ) {
        downstream = aDownstream
    }

    @Override
    UserModel loadUserData( String userID ) {
        // another option would be to provide a default object instead of throwing an error
        downstream.fetchUser( userID ).orElseThrow( { new IllegalStateException( "No such user: ${userID}" )  } )
    }
}
