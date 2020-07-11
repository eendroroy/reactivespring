package com.github.eendroroy.reactivespring.config

import com.mongodb.reactivestreams.client.MongoClient
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory

/**
 * @author indrajit
 */
@Profile(value = ["local"])
@Configuration
@Import(EmbeddedMongoAutoConfiguration::class)
class DataConfig {
    companion object {
        const val DATABASE_NAME = "reservations"
    }

    @Bean
    fun mongoDatabaseFactory(mongoCLient: MongoClient): ReactiveMongoDatabaseFactory {
        return SimpleReactiveMongoDatabaseFactory(mongoCLient, DATABASE_NAME)
    }

    @Bean
    fun reactiveMongoTemplate(reactiveMongoDatabaseFactory: ReactiveMongoDatabaseFactory): ReactiveMongoOperations {
        return ReactiveMongoTemplate(reactiveMongoDatabaseFactory)
    }
}