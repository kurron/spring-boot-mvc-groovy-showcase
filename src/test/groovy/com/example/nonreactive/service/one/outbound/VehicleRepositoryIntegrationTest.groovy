package com.example.nonreactive.service.one.outbound

import org.junit.experimental.categories.Category
import org.kurron.categories.OutboundIntegrationTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import spock.lang.Specification

/**
 * Integration test of the {@link VehicleRepository} object.  This is another form of auto-configured test.
 */
@Category( OutboundIntegrationTest )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DataMongoTest
class VehicleRepositoryIntegrationTest extends Specification {

    @Autowired
    private MongoTemplate template

    @Autowired
    private VehicleRepository repository

    void setup() {
        assert template
        assert repository
    }

    void testReading() {
        expect:
        def persisted = new VehicleEntity( id: 'one', make: 'Ferrari', model: 'F12berlinetta' )
        template.save( persisted )
        def found = repository.findById( persisted.id ).get()
        persisted == found
    }
}
