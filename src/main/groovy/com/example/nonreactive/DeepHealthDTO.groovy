package com.example.nonreactive

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

/**
 * Data transfer object containing the results of the "deep" health check.
 */
@Canonical
class DeepHealthDTO {

    @JsonProperty( 'status' )
    String message
}
