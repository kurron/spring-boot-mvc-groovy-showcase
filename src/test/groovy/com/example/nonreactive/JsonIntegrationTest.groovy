package com.example.nonreactive

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import org.junit.experimental.categories.Category
import org.kurron.categories.OutboundIntegrationTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.BasicJsonTester
import org.springframework.boot.test.json.JacksonTester
import spock.lang.Specification

@Category( OutboundIntegrationTest )
@JsonTest
class JsonIntegrationTest extends Specification {

    @Autowired
    JacksonTester<Vehicle> json

    // GSON is another option
    //GsonTester<Vehicle> gson

    // JSONB is another option
    //JsonbTester<Vehicle> jsonb

    @Autowired
    BasicJsonTester basic

    void setup() {
        assert json
        assert basic
    }

    void testRoundTripViaJacksonTester() {
        expect:
        def vehicle = new Vehicle( make: 'make', model: "model" )
        def encoded = json.write( vehicle ).json
        def decoded = json.parse( encoded ).object
        vehicle == decoded
    }

    void testRoundTripViaBasicJsonTester() {
        expect:
        def vehicle = new Vehicle( make: 'make', model: "model" )
        def encoded = json.write( vehicle ).json
        def expected = basic.from( '{ "alpha": "make", "bravo": "model" }' ).json
        // different because of the formatting -- very odd
        encoded != expected
    }

    @Canonical
    static class Vehicle {

        @JsonProperty( 'alpha' )
        String make

        @JsonProperty( 'bravo' )
        String model
    }
}
