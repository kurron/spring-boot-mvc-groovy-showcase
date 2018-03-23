package com.example.nonreactive.service.one.outbound

import com.example.nonreactive.service.one.shared.UserModel
import com.example.nonreactive.service.one.shared.UserPort
import groovy.util.logging.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestOperations

/**
 * The responsibility of an outbound gateway to translate the internal model into the
 * communication technology used by the external service.  The idea is to insulate the core
 * from changes in external messaging format and protocol enhancements. A production implementation of the
 * {@link UserPort} service.
 */
@Slf4j
class OutboundGateway implements UserPort {

    /**
     * Manages HTTP interactions.
     */
    private final RestOperations template

    OutboundGateway( RestOperations aTemplate ) {
        template = aTemplate
    }

    @Override
    Optional<UserModel> fetchUser( String userID ) {
        //TODO: install a circuit breaker here
        log.info( 'Contacting downstream service for information on user {}', userID )
        log.info( 'Due to caching, this call should only be made once per user.' )
        ResponseEntity<UserDTO> response = template.getForEntity( 'https://randomuser.me/api?seed={userID}', UserDTO, userID )
        def dto = response.body.results.first()
        Optional.of( new UserModel( email: dto.email, username: dto.login.username, password: dto.login.password ) )
    }
}
