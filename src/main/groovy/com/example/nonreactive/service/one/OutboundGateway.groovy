package com.example.nonreactive.service.one

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.web.client.RestOperations

class OutboundGateway implements  UserPort {

    private final RestOperations template

    OutboundGateway( RestTemplateBuilder builder ) {
        template = builder.build()
    }

    @Override
    String fetchUser() {
        template.getForEntity( 'https://randomuser.me/api', String )
    }
}
