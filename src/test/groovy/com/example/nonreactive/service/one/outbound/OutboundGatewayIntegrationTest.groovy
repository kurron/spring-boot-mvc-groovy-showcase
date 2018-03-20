package com.example.nonreactive.service.one.outbound

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess

import com.example.nonreactive.OutboundConfiguration
import org.junit.experimental.categories.Category
import org.kurron.categories.OutboundIntegrationTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.client.MockRestServiceServer
import spock.lang.Specification

/**
 * Integration test of the {@link OutboundGateway} object.  This test is different from what we are used to because
 * a fake server is involved.  There should be at least one test that hits a real service to verify things are working.
 * This form smells more like a contract test than an integration test.
 */
@Category( OutboundIntegrationTest )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = [OutboundConfiguration])
@RestClientTest( components = [OutboundGateway] )
class OutboundGatewayIntegrationTest extends Specification {

    @Autowired
    private OutboundGateway service

    @Autowired
    private MockRestServiceServer server

    void setup() {
        assert service
        assert server
    }

    // another mechanism for providing a Service Stub.  The actual service is never hit, being completely stubbed.
    void fetchUser() {
        expect:
        server.expect( requestTo('https://randomuser.me/api?seed=orangecat512' ) )
              .andRespond( withSuccess( canned, APPLICATION_JSON ) )
        def response = service.fetchUser( 'orangecat512' ).get()
        with( response ) {
            username == 'orangecat512'
            password == 'blazers'
            email == 'luna.richard@example.com'
        }
    }

    static final canned =
'''
{
  "results": [
    {
      "gender": "female",
      "name": {
        "title": "mademoiselle",
        "first": "luna",
        "last": "richard"
      },
      "location": {
        "street": "1126 rue dugas-montbel",
        "city": "bussy-chardonney",
        "state": "thurgau",
        "postcode": 2020
      },
      "email": "luna.richard@example.com",
      "login": {
        "username": "orangecat512",
        "password": "blazers",
        "salt": "LYKSYov3",
        "md5": "3301ee205ffb601a68d27088af05cf01",
        "sha1": "cbd3cf4b9693cc7d5c825800f097865212f449a8",
        "sha256": "e4ab7edf5252638adfe8d82e41c0d0d2fc4e124d64b3cc7ddafff0c3ce88d6ed"
      },
      "dob": "1944-09-27 06:33:06",
      "registered": "2006-12-07 02:34:57",
      "phone": "(778)-334-8279",
      "cell": "(893)-225-0426",
      "id": {
        "name": "AVS",
        "value": "756.AACR.NODD.82"
      },
      "picture": {
        "large": "https://randomuser.me/api/portraits/women/44.jpg",
        "medium": "https://randomuser.me/api/portraits/med/women/44.jpg",
        "thumbnail": "https://randomuser.me/api/portraits/thumb/women/44.jpg"
      },
      "nat": "CH"
    }
  ],
  "info": {
    "seed": "281b549df15ea3ac",
    "results": 1,
    "page": 1,
    "version": "1.1"
  }
}
'''
}
