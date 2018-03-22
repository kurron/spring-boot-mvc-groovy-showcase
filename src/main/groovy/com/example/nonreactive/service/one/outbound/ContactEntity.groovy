package com.example.nonreactive.service.one.outbound

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

/**
 * An example Redis entity that stores a person's contact information.
 * The Jackson annotations exist so we have the choice of using either a mapper
 * or a repository.
 */
@Canonical
@RedisHash( 'contact' )
class ContactEntity {

    @Id
    @JsonProperty( 'username' )
    String username

    @JsonProperty( 'email' )
    String email

    @JsonProperty( 'cell' )
    String cell
}
