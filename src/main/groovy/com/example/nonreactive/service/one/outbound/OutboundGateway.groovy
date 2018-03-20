package com.example.nonreactive.service.one.outbound

import com.example.nonreactive.service.one.shared.UserModel
import com.example.nonreactive.service.one.shared.UserPort
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestOperations

/**
 * The responsibility of an outbound gateway to translate the internal model into the
 * communication technology used by the external service.  The idea is to insulate the core
 * from changes in external messaging format and protocol enhancements. A production implementation of the
 * {@link UserPort} service.
 */
class OutboundGateway implements UserPort {

    /**
     * Manages HTTP interactions.
     */
    private final RestOperations template

    OutboundGateway( RestTemplateBuilder builder ) {
        template = builder.build()
    }

    @Override
    UserModel fetchUser( String userID ) {
        ResponseEntity<UserDTO> response = template.getForEntity( 'https://randomuser.me/api?seed={userID}', UserDTO, userID )
        def dto = response.body.results.first()
        new UserModel( email: dto.email, username: dto.login.username, password: dto.login.password )
    }
}
