package com.example.nonreactive.service.one.outbound

import groovy.transform.Canonical
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * This object can be persisted to the database.
 */
@Canonical
@Entity
class UserEntity {

    @Id
    @GeneratedValue
    Long id

    String username
}
