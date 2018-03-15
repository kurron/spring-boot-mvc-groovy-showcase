package com.example.nonreactive.service.one

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import com.example.nonreactive.shared.ApplicationProperties
import org.junit.experimental.categories.Category
import org.kurron.categories.DocumentationTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@Category( DocumentationTest )
@WebMvcTest( controllers = [InboundGateway] )
@AutoConfigureRestDocs( uriScheme = 'https', uriHost = 'api.example.com', uriPort = 443 )
class InboundGatewayDocumentationTest extends Specification {


    @Autowired
    private MockMvc mvc

    @Autowired
    private ApplicationProperties configuration

    void setup() {
        assert mvc
        assert configuration
    }

    void documentGet() {
        expect:
        mvc.perform( get( '/instance' ) )
           .andExpect( status().isOk() )
           .andDo( document( 'get-instance' ) )
    }

    @TestConfiguration
    static class Configuration {

        @Bean
        MockMeterRegistry mockMeterRegistry() {
            new MockMeterRegistry()
        }
    }
}
