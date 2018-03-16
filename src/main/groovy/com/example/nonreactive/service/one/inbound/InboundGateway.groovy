package com.example.nonreactive.service.one.inbound

import com.example.nonreactive.shared.ApplicationProperties
import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InboundGateway {

    private final ApplicationProperties configuration

    // to test Prometheus support
    private final Counter counter

    InboundGateway( ApplicationProperties aConfiguration, MeterRegistry registry ) {
        configuration = aConfiguration
        counter = registry.counter( 'service.one.handled', 'tag-one', 'tag-two' )
    }

    @GetMapping( path = '/instance', produces = [MediaType.TEXT_PLAIN_VALUE] )
    ResponseEntity<String> fetchInstanceID() {
        counter.increment()
        new ResponseEntity<String>( configuration.instance as String, HttpStatus.OK )
    }
}
