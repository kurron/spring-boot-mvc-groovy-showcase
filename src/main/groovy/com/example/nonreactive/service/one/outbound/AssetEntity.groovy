package com.example.nonreactive.service.one.outbound

import groovy.util.logging.Slf4j
import org.neo4j.ogm.annotation.*

/**
 * This object can be persisted to the database.
 */
@Slf4j
@NodeEntity( label = 'Asset' )
class AssetEntity {

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
     * Asset's description.
     */
    @Property( name = 'description' )
    String description

    @Labels
    List<String> labels = [ 'Image', 'PNG' ]

    /**
     * Called when the entity is loaded.
     */
    @PostLoad
    void postLoad() {
        log.debug( 'Just loaded entity {}', id )
    }

}
