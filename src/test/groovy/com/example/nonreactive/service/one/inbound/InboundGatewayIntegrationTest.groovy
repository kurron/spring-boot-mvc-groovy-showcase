package com.example.nonreactive.service.one.inbound

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

import com.example.nonreactive.service.one.MockMeterRegistry
import com.example.nonreactive.shared.ApplicationProperties
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.experimental.categories.Category
import org.kurron.categories.InboundIntegrationTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

/**
 * Integration test of the {@link InboundGateway} object.
 */
@Category( InboundIntegrationTest )
@WebMvcTest( controllers = [InboundGateway] )
class InboundGatewayIntegrationTest extends Specification {


    @Autowired
    private MockMvc mvc

    @Autowired
    private ApplicationProperties configuration

    @Autowired
    private ObjectMapper mapper

    def expectedID = UUID.randomUUID()

    void setup() {
        assert mvc
        assert configuration
        assert mapper
        configuration.instance = expectedID
    }

    void testGet() {
        expect:
        def response = mvc.perform( get( '/instance' ) ).andReturn().response
        MediaType.parseMediaType( response.contentType ).isCompatibleWith( MediaType.APPLICATION_JSON )
        def control = mapper.readValue( response.contentAsByteArray, HypermediaControl )
        control.instance == expectedID
    }

    @TestConfiguration
    static class Configuration {

        @Bean
        MockMeterRegistry mockMeterRegistry() {
            new MockMeterRegistry()
        }

        @Bean
        ProcessorServiceStub processorServiceStub() {
            new ProcessorServiceStub()
        }
    }
}
