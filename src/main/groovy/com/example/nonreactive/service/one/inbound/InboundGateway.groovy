package com.example.nonreactive.service.one.inbound

import com.example.nonreactive.service.one.core.Processor
import com.example.nonreactive.shared.ApplicationProperties
import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * An inbound gateway is responsible for converting a communication technology, such as REST, into the internal
 * model that the core uses.  The idea is to insulate the core from changes to protocols and external message formats.
 * On the inbound side, direction of the Ports and Adapters connection of the Hexagonal architecture is Adapter->Port.
 * We are adapting from HTTP to a call to a Port.
 */
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

    @GetMapping( path = '/instance', produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE] )
    ResponseEntity<HypermediaControl> fetchInstanceID() {
        //throw new CustomException( 'Forced to fail!' )
        counter.increment()
        def dto = new HypermediaControl( instance: configuration.instance )
        new ResponseEntity<HypermediaControl>( dto, HttpStatus.OK )
    }

    @GetMapping( path = '/user/{userID}', produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE] )
    ResponseEntity<HypermediaControl> fetchUser( @PathVariable String userID ) {
        counter.increment()
        def model = processor.loadUserData( userID )
        def user = new HypermediaControl.User( email: model.email, username: model.username )
        def dto = new HypermediaControl( user: user )
        new ResponseEntity<HypermediaControl>( dto, HttpStatus.OK )
    }
}
