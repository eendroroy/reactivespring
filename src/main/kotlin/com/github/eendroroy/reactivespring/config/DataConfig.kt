package com.github.eendroroy.reactivespring.config

import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Profile

/**
 * @author indrajit
 */
@Profile(value = ["local"])
@Configuration
@Import(EmbeddedMongoAutoConfiguration::class)
class DataConfig {

}