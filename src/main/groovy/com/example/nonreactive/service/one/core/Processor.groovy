package com.example.nonreactive.service.one.core

import com.example.nonreactive.service.one.shared.UserModel

/**
 * The central logic in the Heaxagonal architecture.  Should never have direct access
 * to anything outside the process.  Incoming data should be handed to it from an inbound
 * gateway and requests leaving the process should be handled by an outbound gateway.
 */
interface Processor {

    /**
     * Fetches data about the specified user.
     * @param userid which user to load.
     * @return found information.
     */
    UserModel loadUserData( String userid )
}
