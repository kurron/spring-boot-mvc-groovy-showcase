package com.example.nonreactive.service.one.shared

import org.springframework.cache.annotation.Cacheable

/**
 * A Port to an external, downstream service.
 */
interface UserPort {

    /**
     * Load data for the specified user.
     * @param userID user to load.
     * @return loaded user. The use of Optional is just one alternative for handling an unknown user.
     */
    @Cacheable ( 'user-data' )
    Optional<UserModel> fetchUser( String userID )
}