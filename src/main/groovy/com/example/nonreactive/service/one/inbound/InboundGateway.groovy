package com.example.nonreactive.service.one.inbound

import com.example.nonreactive.service.one.core.Processor
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

    /**
     * Core service that does the actual work.
     */
    private final Processor processor

    InboundGateway( ApplicationProperties aConfiguration,
                    MeterRegistry registry,
                    Processor aProcessor ) {
        configuration = aConfiguration
        counter = registry.counter( 'service.one.handled', 'tag-one', 'tag-two' )
        processor = aProcessor
    }

    @GetMapping( path = '/instance', produces = [MediaType.TEXT_PLAIN_VALUE] )
    ResponseEntity<String> fetchInstanceID() {
        counter.increment()
        new ResponseEntity<String>( configuration.instance as String, HttpStatus.OK )
    }

    @GetMapping( path = '/user', produces = [MediaType.APPLICATION_JSON_VALUE] )
    ResponseEntity<HypermediaControl> fetchUser() {
        counter.increment()
        def model = processor.loadUserData( 'FOO' )
        def user = new HypermediaControl.User( email: model.email, username: model.username )
        def dto = new HypermediaControl( user: user )
        new ResponseEntity<HypermediaControl>( dto, HttpStatus.OK )
    }
}
