package com.example.nonreactive.service.one.core

import com.example.nonreactive.service.one.shared.UserModel
import com.example.nonreactive.service.one.shared.UserPort

/**
 * Core component that holds key business logic, leaving the integration plumbing
 * to the gateways.
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
        downstream.fetchUser( userID )
    }
}
