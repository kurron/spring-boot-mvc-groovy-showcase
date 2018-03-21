package com.example.nonreactive.service.one.inbound

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

/**
 * DTO specific to this gateway.  In practice, a more complex service with several gateways
 * would probably share a control and it would live in the shared package.
 */
@Canonical
class HypermediaControl {

    @JsonProperty( 'instance' )
    UUID instance

    @JsonProperty( 'user-information' )
    User user

    @JsonProperty( 'error' )
    Error error

    static class User {

        @JsonProperty( 'username' )
        String username

        @JsonProperty( 'e-mail' )
        String email
    }

    static class Error {
        @JsonProperty( 'message' )
        String message

        @JsonProperty( 'developer-message' )
        String developerMessage
    }
}
