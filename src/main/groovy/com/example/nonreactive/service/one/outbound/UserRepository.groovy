package com.example.nonreactive.service.one.outbound

import org.springframework.data.repository.CrudRepository

/**
 * Outbound gateway to the SQL store.
 */
interface UserRepository extends CrudRepository<UserEntity, Long> {

}