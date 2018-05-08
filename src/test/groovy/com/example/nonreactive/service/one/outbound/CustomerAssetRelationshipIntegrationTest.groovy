package com.example.nonreactive.service.one.outbound

import org.junit.experimental.categories.Category
import org.kurron.categories.OutboundIntegrationTest
import org.neo4j.ogm.session.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/**
 * Integration test of the {@link CustomRelationshipEntity} object.  This is another form of auto-configured test.
 */
@Category( OutboundIntegrationTest )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.NONE )
@DataNeo4jTest
class CustomerAssetRelationshipIntegrationTest extends Specification {

    @Autowired
    private Session entityManager

    @Autowired
    private CustomerRepository repository

    void setup() {
        assert entityManager
        assert repository
    }

    void 'exercise one-to-one relationship'() {
        def asset = saveAsset()
        def customer = saveCustomer()
        def favorite = new IsFavoriteRelationshipEntity( customer: customer, asset: asset, times: 1 )
        customer.favorite = favorite

        expect:
        repository.save( customer )
        def found = repository.findById( customer.id ).get()
        asset.id == found.favorite.id
    }

    void 'exercise one-to-many relationship'() {
        def assets = (1..100).collect { saveAsset() }
        def customer = saveCustomer()
        assets.each {
            def hasSeen = new CustomRelationshipEntity( customer: customer, asset: it, times: 1 )
            customer.hasSeen.add( hasSeen )
        }
        entityManager.save( customer )

        expect:
        def found = repository.findById( customer.id ).get()
        found.hasSeen.size() == assets.size()
    }

    private CustomerEntity saveCustomer() {
        def entity = new CustomerEntity( name: 'Random' )
        entityManager.save( entity )
        entity
    }

    private AssetEntity saveAsset() {
        def entity = new AssetEntity( description: 'Random' )
        entityManager.save( entity )
        entity
    }
}
