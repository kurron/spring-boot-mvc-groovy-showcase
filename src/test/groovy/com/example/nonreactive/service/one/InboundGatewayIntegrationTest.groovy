package com.example.nonreactive.service.one

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

import com.example.nonreactive.shared.ApplicationProperties
import org.junit.experimental.categories.Category
import org.kurron.categories.InboundIntegrationTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@Category( InboundIntegrationTest )
@WebMvcTest( controllers = [InboundGateway] )
class InboundGatewayIntegrationTest extends Specification {


    @Autowired
    private MockMvc mvc

    @Autowired
    private ApplicationProperties configuration

    def expectedID = UUID.randomUUID()

    void setup() {
        assert mvc
        assert configuration
        configuration.instance = expectedID
    }

    void testGet() {
        expect:
        def response = mvc.perform( get( '/instance' ) ).andReturn().response
        MediaType.parseMediaType( response.contentType ).isCompatibleWith( MediaType.TEXT_PLAIN )
        response.contentAsString == expectedID as String
    }

    @TestConfiguration
    static class Configuration {

        @Bean
        MockMeterRegistry mockMeterRegistry() {
            new MockMeterRegistry()
        }
    }
}
