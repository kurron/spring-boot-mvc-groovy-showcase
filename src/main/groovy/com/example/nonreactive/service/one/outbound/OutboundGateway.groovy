package com.example.nonreactive.service.one.outbound

import com.example.nonreactive.service.one.shared.UserModel
import com.example.nonreactive.service.one.shared.UserPort
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestOperations

class OutboundGateway implements  UserPort {

    private final RestOperations template

    OutboundGateway( RestTemplateBuilder builder ) {
        template = builder.build()
    }

    @Override
    UserModel fetchUser() {
        ResponseEntity<UserDTO> response = template.getForEntity( 'https://randomuser.me/api', UserDTO )
        def dto = response.body.results.first()
        new UserModel( email: dto.email, username: dto.login.username, password: dto.login.password )
    }
}
