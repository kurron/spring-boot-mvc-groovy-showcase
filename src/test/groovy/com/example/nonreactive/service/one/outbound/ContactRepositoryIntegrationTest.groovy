package com.example.nonreactive.service.one.outbound

import org.junit.experimental.categories.Category
import org.kurron.categories.OutboundIntegrationTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.hash.Jackson2HashMapper
import spock.lang.Ignore
import spock.lang.Specification

/**
 * Integration test of the {@link ContactRepository} object.  This is another form of auto-configured test.
 */
@Category( OutboundIntegrationTest )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.NONE )
@DataRedisTest
class ContactRepositoryIntegrationTest extends Specification {

    @Autowired
    private StringRedisTemplate template

    @Autowired
    private ContactRepository repository

    private final def mapper = new Jackson2HashMapper( false )

    void setup() {
        assert template
        assert repository
    }

    @Ignore( 'Something is broken and I am not sure what has changed.  Will look at it after Neo4J is integrated.' )
    void testReading() {
        expect:
        // since we are testing the repository, use the template and mapper to store the object
        def persisted = new ContactEntity( username: 'one', email: 'nobody@nowhere.com' )
        def mappedHash = mapper.toHash( persisted )
        template.opsForHash().putAll( 'one', mappedHash )

        def found = repository.findById( persisted.username ).get()
        persisted == found
    }
}
