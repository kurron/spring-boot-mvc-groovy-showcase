package com.example.nonreactive.service.one.outbound

import com.example.nonreactive.service.one.shared.UserModel

/**
 * Production implementation of the {@link SaveItPort}.
 */
class SaveItGateway implements SaveItPort {

    /**
     * Downstream service that handles relational persistence.
     */
    private final UserRepository relational

    /**
     * Downstream service that handles document persistence.
     */
    private final VehicleRepository document

    /**
     * Downstream service that handles key-value persistence.
     */
    private final ContactRepository keyValue

    SaveItGateway( UserRepository relational, VehicleRepository document, ContactRepository keyValue ) {
        this.relational = relational
        this.document = document
        this.keyValue = keyValue
    }

    @Override
    void persistToRelationalStore( UserModel model ) {
        // this is just show we can write to a relational database
        def user = new UserEntity( username: model.username )
        relational.save( user )
    }

    @Override
    void persistToDocumentStore( UserModel model ) {
        // this is just to show we can write to a document database
        def vehicle = new VehicleEntity( id: model.username, make: 'Ferrari', model: '488GTB' )
        document.save( vehicle )
    }

    @Override
    void persistToKeyValueStore( UserModel model ) {
        // this is just to show we can write to a key-value database
        def contact = new ContactEntity( username: model.username, email: model.email )
        keyValue.save( contact )
    }
}
