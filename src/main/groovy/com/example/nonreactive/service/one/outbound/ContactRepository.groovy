package com.example.nonreactive.service.one.outbound

import org.springframework.data.keyvalue.repository.KeyValueRepository

/**
 * Outbound gateway the interacts with Redis.
 */
interface ContactRepository extends KeyValueRepository<ContactEntity, String> {

}