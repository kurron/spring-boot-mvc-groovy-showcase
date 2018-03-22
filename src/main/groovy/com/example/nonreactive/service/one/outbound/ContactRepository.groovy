package com.example.nonreactive.service.one.outbound

import org.springframework.data.repository.CrudRepository

/**
 * Outbound gateway the interacts with Redis.
 */
interface ContactRepository extends CrudRepository<ContactEntity, String> {

}