package com.example.nonreactive.service.one.inbound

import com.example.nonreactive.shared.ApplicationProperties
import org.junit.experimental.categories.Category
import org.kurron.categories.InboundIntegrationTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import spock.lang.Specification

/**
 * I'm calling this an acceptance test because we are hitting an actual external endpoint.
 * A test of the {@link InboundGateway} object. I probably need change this to an integration test
 * and call the one using a mock service something else.
 */
@Category( InboundIntegrationTest )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InboundGatewayAcceptanceTest extends Specification {

    @Autowired
    private TestRestTemplate template

    @Autowired
    private ApplicationProperties configuration

    void setup() {
        assert template
        assert configuration
    }

    void fetchUser() {
        expect:
        def response = template.getForEntity( '/instance', String )
        response.statusCode == HttpStatus.OK
        response.body == configuration.instance as String
    }

    @TestConfiguration
    static class Configuration {

        @Bean
        RestTemplateBuilder restTemplateBuilder() {
            new RestTemplateBuilder().setConnectTimeout( 1000 ).setReadTimeout( 1000 )
        }
    }
}
