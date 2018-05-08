package com.example.nonreactive.service.one.outbound

import org.neo4j.ogm.annotation.*

/**
 * This object can be persisted to the database.
 */
@NodeEntity( label = 'Asset' )
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

    @Labels
    List<String> labels = [ 'Image', 'PNG' ]

}
