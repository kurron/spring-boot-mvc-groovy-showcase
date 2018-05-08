package com.example.nonreactive.service.one.outbound

import groovy.util.logging.Slf4j
import org.neo4j.ogm.annotation.*

/**
 * This object can be persisted to the database.
 */
@Slf4j
@RelationshipEntity( type = 'HAS_SEEN' )
class CustomRelationshipEntity {

    /**
     * Primary key.
     */
    @Id
    @GeneratedValue
    Long id

    /**
     * Optimistic locking.
     */
    @Version
    Long version

    /**
     * How many times has the customer seen the asset.
     */
    @Property( name = 'times' )
    int times

    @StartNode
    CustomerEntity customer

    @EndNode
    AssetEntity asset

    /**
     * Called when the entity is loaded.
     */
    @PostLoad
    void postLoad() {
        log.debug( 'Just loaded entity {}', id )
    }

}
