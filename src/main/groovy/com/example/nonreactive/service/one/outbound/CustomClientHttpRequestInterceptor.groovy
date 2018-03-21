package com.example.nonreactive.service.one.outbound

import groovy.util.logging.Slf4j
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse

/**
 * A silly example on how we can put an interceptor around HTTP calls to gather additional information.
 */
@Slf4j
class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    ClientHttpResponse intercept( HttpRequest request, byte[] body, ClientHttpRequestExecution execution ) {
        log.debug( 'About to {} {}', request.methodValue, request.URI as String )
        execution.execute( request, body )
    }
}
