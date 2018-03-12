package com.example.nonreactive

import org.springframework.boot.actuate.info.Info
import org.springframework.boot.actuate.info.InfoContributor

/**
 * Provides data for the /operations/info endpoint using suggestions from
 * https://pragprog.com/book/mnee2/release-it-second-edition.
 */
class CustomInfoContributor implements InfoContributor {

    /**
     * Default value if we cannot determine a property's value.
     */
    private static final String UNKNOWN = 'unknown'

    @Override
    void contribute( final Info.Builder builder ) {
        builder.withDetail( 'java-runtime', obtainJavaDetails() )
        builder.withDetail( 'operating-system', obtainOperatingSystemDetails() )
        builder.withDetail( 'user', obtainUserDetails() )
    }

    private static Map<String, String> obtainJavaDetails() {
        ['vendor': System.getProperty( 'java.vendor', UNKNOWN ),
         'version': System.getProperty( 'java.version', UNKNOWN )]
    }

    private static Map<String, String> obtainOperatingSystemDetails() {
        ['architecture': System.getProperty( 'os.arch', UNKNOWN ),
         'name': System.getProperty( 'os.name', UNKNOWN ),
         'version': System.getProperty( 'os.version', UNKNOWN )]
    }

    private static Map<String, String> obtainUserDetails() {
        ['name': System.getProperty( 'user.name', UNKNOWN ),
         'home-directory': System.getProperty( 'user.home', UNKNOWN ),
         'working-directory': System.getProperty( 'user.dir', UNKNOWN )]
    }
}