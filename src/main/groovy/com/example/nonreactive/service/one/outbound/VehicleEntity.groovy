package com.example.nonreactive.service.one.outbound

import groovy.transform.Canonical
import javax.persistence.Id
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
