package com.example.nonreactive.service.one.shared

import groovy.transform.Canonical

/**
 * Representation of a user specific to the service.  All core objects reference the model
 * and gateways will translate external DTOs into the model.
 */
@Canonical
class UserModel {

    /**
     * The user-s e-mail address.
     */
    String email

    /**
     * The user-s login name.
     */
    String username

    /**
     * The user-s password.
     */
    String password
}
