package com.example.nonreactive.service.one.outbound

import groovy.util.logging.Slf4j
import org.neo4j.ogm.annotation.*

/**
 * This object can be persisted to the database.
 */
@Slf4j
@NodeEntity( label = 'Customer' )
class CustomerEntity {

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
     * Customer's full name.
     */
    @Property( name = 'name' )
    String name

    @Labels
    List<String> labels = [ 'Person', 'Account' ]

    /**
     * Relates customer to favorite asset.
     */
    @Relationship( type = 'IS_FAVORITE', direction = Relationship.UNDIRECTED )
    IsFavoriteRelationshipEntity favorite

    /**
     * Relates customer to seen assets.
     */
    @Relationship( type = 'HAS_SEEN', direction = Relationship.OUTGOING )
    Set<CustomRelationshipEntity> hasSeen = []

    /**
     * Called when the entity is loaded.
     */
    @PostLoad
    void postLoad() {
        log.debug( 'Just loaded entity {}', id )
    }
}
