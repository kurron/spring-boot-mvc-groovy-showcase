package com.example.nonreactive.service.one.outbound

import org.junit.experimental.categories.Category
import org.kurron.categories.OutboundIntegrationTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/**
 * Integration test of the {@link UserRepository} object.  This is another form of auto-configured test.
 */
@Category( OutboundIntegrationTest )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DataJpaTest
class UserRepositoryIntegrationTest extends Specification {

    @Autowired
    private TestEntityManager entityManager

    @Autowired
    private UserRepository repository

    void setup() {
        assert entityManager
        assert repository
    }

    void testReading() {
        expect:
        def persisted = entityManager.persist( new UserEntity( username: 'bob' ) )
        def found = repository.findById( persisted.id ).get()
        persisted == found
    }
}
