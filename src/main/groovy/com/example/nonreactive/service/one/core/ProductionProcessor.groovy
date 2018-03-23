package com.example.nonreactive.service.one.core

import com.example.nonreactive.service.one.outbound.SaveItPort
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
     * Service that knows how to persist things to storage.
     */
    private final SaveItPort storage

    ProductionProcessor( UserPort downstream, SaveItPort storage ) {
        this.downstream = downstream
        this.storage = storage
    }

    @Override
    UserModel loadUserData( String userID ) {
        // another option would be to provide a default object instead of throwing an error
        def model = downstream.fetchUser( userID ).orElseThrow( { new IllegalStateException( "No such user: ${userID}" )  } )
        storage.persistToRelationalStore( model )
        storage.persistToDocumentStore( model )
        storage.persistToKeyValueStore( model )
        model
    }
}
