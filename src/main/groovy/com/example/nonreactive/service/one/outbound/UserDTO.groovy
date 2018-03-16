package com.example.nonreactive.service.one.outbound

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

/**
 * Data Transfer Object representing the information coming back from the external service.
 */
@Canonical
class UserDTO {

    @JsonProperty( 'results' )
    List<Result> results

    @Canonical
    static class Result {

        @JsonProperty( 'cell' )
        String cell

        @JsonProperty( 'dob' )
        String dateOfBirth

        @JsonProperty( 'email' )
        String email

        @JsonProperty( 'login' )
        Login login

        @JsonProperty( 'name' )
        Login name
    }

    @Canonical
    static class Login {
        @JsonProperty( 'md5' )
        String md5

        @JsonProperty( 'password' )
        String password

        @JsonProperty( 'salt' )
        String salt

        @JsonProperty( 'sha1' )
        String sha1

        @JsonProperty( 'sha256' )
        String sha256

        @JsonProperty( 'username' )
        String username
    }

    @Canonical
    static class Name {
        @JsonProperty( 'first' )
        String first

        @JsonProperty( 'last' )
        String last

        @JsonProperty( 'title' )
        String title
    }
}
