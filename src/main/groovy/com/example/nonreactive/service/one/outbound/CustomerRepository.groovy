package com.example.nonreactive.service.one.outbound

import org.springframework.data.neo4j.repository.Neo4jRepository

/**
 * Outbound gateway to the graph store.
 */
interface CustomerRepository extends Neo4jRepository<CustomerEntity,Long> {
}
