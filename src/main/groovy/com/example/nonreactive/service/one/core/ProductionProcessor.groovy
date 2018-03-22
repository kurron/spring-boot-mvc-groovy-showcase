package com.example.nonreactive.service.one.core

import com.example.nonreactive.service.one.outbound.UserEntity
import com.example.nonreactive.service.one.outbound.UserRepository
import com.example.nonreactive.service.one.outbound.VehicleEntity
import com.example.nonreactive.service.one.outbound.VehicleRepository
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
     * Downstream service that handles relational persistence.
     */
    private final UserRepository relational

    /**
     * Downstream service that handles document persistence.
     */
    private final VehicleRepository document

    ProductionProcessor( UserPort port, UserRepository aRelational, VehicleRepository aDocument ) {
        downstream = port
        relational = aRelational
        document = aDocument
    }

    @Override
    UserModel loadUserData( String userID ) {
        // another option would be to provide a default object instead of throwing an error
        def model = downstream.fetchUser( userID ).orElseThrow( { new IllegalStateException( "No such user: ${userID}" )  } )

        // this is just show we can write to a relational database
        def user = new UserEntity( username: model.username )
        relational.save( user )

        // this is just to show we can write a document database
        def vehicle = new VehicleEntity( id: model.username, make: 'Ferrari', model: '488GTB' )
        document.save( vehicle )

        model
    }
}
