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
class CustomerRepositoryIntegrationTest extends Specification {

    @Autowired
    private Session entityManager

    @Autowired
    private CustomerRepository repository

    void setup() {
        assert entityManager
        assert repository
    }

    void testReading() {
        def entity = new CustomerEntity( name: 'Reading' )

        expect:
        entityManager.save( entity )
        def found = repository.findById( entity.id ).get()
        entity == found
    }

    void testWriting() {
        def entity = new CustomerEntity( name: 'Writing' )

        expect:
        def persisted = repository.save( entity )
        def found = entityManager.load( CustomerEntity, entity.id )
        persisted == found
    }
}
