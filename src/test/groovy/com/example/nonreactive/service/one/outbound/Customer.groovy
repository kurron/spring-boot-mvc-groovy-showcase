package com.example.nonreactive.service.one.outbound

import org.junit.experimental.categories.Category
import org.kurron.categories.OutboundIntegrationTest
import org.neo4j.ogm.session.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/**
 * Integration test of the {@link CustomerRepository} object.  This is another form of auto-configured test.
 */
@Category( OutboundIntegrationTest )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.NONE )
@DataNeo4jTest
class Customer extends Specification {

    @Autowired
    private Session session

    @Autowired
    private CustomerRepository repository

    void setup() {
        assert session
        assert repository
    }

    void testReading() {
        def customer = new CustomerEntity( name: 'Reading' )

        expect:
        session.save( customer )
        def found = repository.findById( customer.id ).get()
        customer == found
    }

    void testWriting() {
        def customer = new CustomerEntity( name: 'Writing' )

        expect:
        def saved = repository.save( customer )
        def read = session.load( CustomerEntity, customer.id )
        saved == read
    }
}
