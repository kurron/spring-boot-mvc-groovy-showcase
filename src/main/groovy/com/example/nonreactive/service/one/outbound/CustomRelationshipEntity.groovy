package com.example.nonreactive.service.one.outbound

import org.neo4j.ogm.annotation.*

/**
 * This object can be persisted to the database.
 */
//@Canonical
@RelationshipEntity( type = 'HAS_SEEN' )
class CustomRelationshipEntity {

    /**
     * Primary key.
     */
    @Id
    @GeneratedValue
    Long id

    /**
     * How many times has the customer seen the asset.
     */
    @Property( name = 'times' )
    int times

    @StartNode
    CustomerEntity customer

    @EndNode
    AssetEntity asset
}
