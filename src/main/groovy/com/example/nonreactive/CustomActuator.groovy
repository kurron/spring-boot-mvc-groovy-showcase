package com.example.nonreactive

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

/**
 * An example of adding a custom endpoint for Operations.  This once simulates a "deep" health check.
 */
@WebEndpoint( id = 'deep-health' )
class CustomActuator {

    @ReadOperation( produces = ['application/vnd.spring-boot.actuator.v2+json', 'application/json'] )
    ResponseEntity<DeepHealthDTO> check() {
        def dto = new DeepHealthDTO( message: 'Looking good' )
        new ResponseEntity<DeepHealthDTO>( dto, HttpStatus.OK )
    }
}
