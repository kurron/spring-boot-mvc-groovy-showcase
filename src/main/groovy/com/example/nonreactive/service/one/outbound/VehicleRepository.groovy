package com.example.nonreactive.service.one.outbound

import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Outbound gateway that interacts with MongoDB.
 */
interface VehicleRepository extends PagingAndSortingRepository<VehicleEntity, String> {
}
