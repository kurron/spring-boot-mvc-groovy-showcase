package com.example.nonreactive

import com.example.nonreactive.shared.ApplicationProperties
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cache.annotation.EnableCaching

/**
 * Application driver.
 */
@SpringBootApplication
@EnableConfigurationProperties( ApplicationProperties )
@EnableCaching
class Application {

	static void main( String[] args ) {
		SpringApplication.run( Application, args )
	}
}
