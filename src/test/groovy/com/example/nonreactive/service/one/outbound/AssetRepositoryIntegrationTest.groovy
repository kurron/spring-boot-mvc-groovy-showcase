package com.example.nonreactive.service.one.outbound

import org.junit.experimental.categories.Category
import org.kurron.categories.OutboundIntegrationTest
import org.neo4j.ogm.session.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/**
 * Integration test of the {@link AssetRepository} object.  This is another form of auto-configured test.
 */
@Category( OutboundIntegrationTest )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.NONE )
@DataNeo4jTest
class AssetRepositoryIntegrationTest extends Specification {

    @Autowired
    private Session entityManager

    @Autowired
    private AssetRepository repository

    void setup() {
        assert entityManager
        assert repository
    }

    void testReading() {
        def entity = new AssetEntity( description: 'Reading' )
        expect:
        entityManager.save( entity )
        def found = repository.findById( entity.id ).get()
        entity == found
    }

    void testWriting() {
        def entity = new AssetEntity( description: 'Writing' )
        expect:
        def persisted = repository.save( entity )
        def found = entityManager.load( AssetEntity, entity.id )
        persisted == found
    }
}
