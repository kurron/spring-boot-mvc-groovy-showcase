package com.example.nonreactive.service.one.outbound

import groovy.transform.Canonical
import org.neo4j.ogm.annotation.EndNode
import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import org.neo4j.ogm.annotation.RelationshipEntity
import org.neo4j.ogm.annotation.StartNode

/**
 * This object can be persisted to the database.
 */
@Canonical
@org.neo4j.ogm.annotation.RelationshipEntity( type = 'HAS_SEEN' )
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
