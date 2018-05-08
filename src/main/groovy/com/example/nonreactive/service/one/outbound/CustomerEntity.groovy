package com.example.nonreactive.service.one.outbound

import org.neo4j.ogm.annotation.*

/**
 * This object can be persisted to the database.
 */
@NodeEntity( label = 'Customer' )
class CustomerEntity {

    /**
     * Primary key.
     */
    @Id
    @GeneratedValue
    Long id

    /**
     * Customer's full name.
     */
    @Property( name = 'name' )
    String name

    @Labels
    List<String> labels = [ 'Person', 'Account' ]

    /**
     * Relates customer to seen assets.
     */
    @Relationship( type = 'HAS_SEEN' )
    CustomRelationshipEntity hasSeen
}
