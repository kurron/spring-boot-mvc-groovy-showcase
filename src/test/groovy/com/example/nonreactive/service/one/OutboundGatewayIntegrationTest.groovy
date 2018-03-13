package com.example.nonreactive.service.one

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
        server.expect( requestTo('https://randomuser.me/api' ) )
              .andRespond( withSuccess( 'hello', APPLICATION_JSON ) )
        def response = service.fetchUser()
        response == '<200,hello,{Content-Type=[application/json]}>'
    }
}
