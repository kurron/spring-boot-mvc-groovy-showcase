package com.example.nonreactive.service.one.inbound

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * A "global" exception handler that produces JSON output in the event of an error.
 */
@ControllerAdvice
class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * This sample is scoped to a single type but in a production application,
     * you would have your exceptions rooted off of a common ancestor, allowing them
     * to be handled here.
     * @param failure what when wrong.
     * @return the control with the error information filled in.
     */
    @ResponseBody
    @ExceptionHandler( CustomException )
    ResponseEntity<HypermediaControl> handleFailure( CustomException failure ) {
        def error = new HypermediaControl.Error( message: failure.message )
        def dto = new HypermediaControl( error: error )
        new ResponseEntity<HypermediaControl>( dto, HttpStatus.INTERNAL_SERVER_ERROR )
    }

    // we override this so that even the errors handled by Spring use the control.
    @Override
    protected ResponseEntity<Object> handleExceptionInternal( Exception failure,
                                                              @Nullable Object body,
                                                              HttpHeaders headers,
                                                              HttpStatus status,
                                                              WebRequest request ) {
        def error = new HypermediaControl.Error( message: failure.message )
        def dto = new HypermediaControl( error: error )
        new ResponseEntity<HypermediaControl>( dto, HttpStatus.INTERNAL_SERVER_ERROR )
    }
}
