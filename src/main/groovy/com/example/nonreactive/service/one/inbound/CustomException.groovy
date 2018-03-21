package com.example.nonreactive.service.one.inbound

/**
 * A fake exception used to test out custom exception handling.
 */
class CustomException extends Exception {
    CustomException( String s ) {
        super( s )
    }
}
