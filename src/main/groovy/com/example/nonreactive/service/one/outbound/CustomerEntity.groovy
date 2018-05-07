package com.example.nonreactive.service.one.outbound

import groovy.transform.Canonical
import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import org.neo4j.ogm.annotation.Relationship

/**
 * This object can be persisted to the database.
 */
@Canonical
@NodeEntity
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

    /**
     * Relates customer to seen assets.
     */
    @Relationship( type = 'HAS_SEEN', direction = Relationship.OUTGOING )
    Set<AssetEntity> hasSeen = [] as Set<AssetEntity>
}
