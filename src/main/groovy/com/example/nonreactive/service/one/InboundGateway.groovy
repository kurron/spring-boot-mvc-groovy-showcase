package com.example.nonreactive.service.one

import com.example.nonreactive.shared.ApplicationProperties
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InboundGateway {

    private final ApplicationProperties configuration

    InboundGateway( ApplicationProperties aConfiguration ) {
        configuration = aConfiguration
    }

    @GetMapping( path = '/instance', produces = [MediaType.TEXT_PLAIN_VALUE] )
    ResponseEntity<String> fetchInstanceID() {
        new ResponseEntity<String>( configuration.instance as String, HttpStatus.OK )
    }
}
