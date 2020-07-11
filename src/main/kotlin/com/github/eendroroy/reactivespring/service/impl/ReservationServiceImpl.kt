package com.github.eendroroy.reactivespring.service.impl

import com.github.eendroroy.reactivespring.model.Reservation
import com.github.eendroroy.reactivespring.service.ReservationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * @author indrajit
 */
@Service
class ReservationServiceImpl @Autowired constructor(
        private val reactiveMongoOperations: ReactiveMongoOperations
) : ReservationService {
    override fun getReservation(id: String?): Mono<Reservation?>? {
        return reactiveMongoOperations.findById(id!!, Reservation::class.java)
    }

    override fun createReservation(reservationMono: Mono<Reservation>): Mono<Reservation?>? {
        return reactiveMongoOperations.save(reservationMono)
    }

    override fun updateReservation(id: String, reservationMono: Mono<Reservation>): Mono<Reservation?>? {
        // Upsert functionality
        //return reactiveMongoOperations.save(reservationMono);

        // Update just price
        return reservationMono.flatMap { reservation ->
            reactiveMongoOperations.findAndModify(
                    Query.query(Criteria.where("id").`is`(id)),
                    Update.update("price", reservation!!.price), Reservation::class.java
            ).flatMap { result ->
                result.price = reservation.price
                Mono.just(result)
            }
        }
    }

    override fun deleteReservation(id: String): Mono<Boolean?>? {
        return reactiveMongoOperations.remove(
                Query.query(Criteria.where("id").`is`(id)), Reservation::class.java)
                .flatMap<Boolean> { deleteResult ->
                    Mono.just(deleteResult.wasAcknowledged())
                }
    }

    override fun listAllReservations(): Flux<Reservation?>? {
        return reactiveMongoOperations.findAll(Reservation::class.java)
    }
}