package com.example.nonreactive.service.one.outbound

import groovy.transform.Canonical
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * MongoDB entity representing a vehicle in the system.
 */
@Document
@Canonical
class VehicleEntity {

    @Id
    String id

    String make

    String model
}
