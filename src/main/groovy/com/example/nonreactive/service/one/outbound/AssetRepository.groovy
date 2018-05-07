package com.example.nonreactive.service.one.outbound

import org.springframework.data.neo4j.repository.Neo4jRepository

/**
 * Outbound gateway to the graph store.
 */
interface AssetRepository extends Neo4jRepository<AssetEntity,Long> {
}
