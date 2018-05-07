package com.example.nonreactive.service.one.outbound

import groovy.transform.Canonical
import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property

/**
 * This object can be persisted to the database.
 */
@Canonical
@NodeEntity
class AssetEntity {

    /**
     * Primary key.
     */
    @Id
    @GeneratedValue
    Long id

    /**
     * Asset's description.
     */
    @Property( name = 'description' )
    String description
}
