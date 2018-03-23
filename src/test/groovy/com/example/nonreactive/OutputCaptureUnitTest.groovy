package com.example.nonreactive

import groovy.util.logging.Slf4j
import org.junit.Rule
import org.springframework.boot.test.rule.OutputCapture
import spock.lang.Specification

/**
 * Unit-test that shows how capture text output. Useful in cases where the SUT does not return any
 * state that can be verified.
 */

class OutputCaptureUnitTest extends Specification {

    @Rule
    OutputCapture capture = new OutputCapture()

    def sut = new NoReturn()

    void 'test a function that does not return anything'() {
        expect:
        sut.doWork( 'foo' )
        capture.toString().contains( 'Just heard foo' )
    }

    @Slf4j
    static class NoReturn {

        void doWork( String work ) {
            log.debug( 'Just heard {}', work )
        }
    }
}
