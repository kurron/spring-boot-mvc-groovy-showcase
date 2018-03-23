package com.example.nonreactive.service.one.outbound

import com.example.nonreactive.service.one.shared.UserModel

/**
 * A silly service that persists data into the various stores.
 */
interface SaveItPort {

    /**
     * Stores data in a relational database.
     * @param model data to store.
     */
    void persistToRelationalStore( UserModel model )

    /**
     * Stores data in a document database.
     * @param model data to store.
     */
    void persistToDocumentStore( UserModel model )


    /**
     * Stores data in a key-value database.
     * @param model data to store.
     */
    void persistToKeyValueStore( UserModel model )
}