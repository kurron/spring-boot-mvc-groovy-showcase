package com.example.nonreactive.service.one.inbound

import com.example.nonreactive.service.one.core.Processor
import com.example.nonreactive.service.one.shared.UserModel

/**
 * Service stub implementation of the {@link Processor} interface.
 */
class ProcessorServiceStub implements Processor {

    @Override
    UserModel loadUserData( String userid ) {
        new UserModel( email: 'nobody@nowhere.com', username: userid, password: 'password' )
    }
}
