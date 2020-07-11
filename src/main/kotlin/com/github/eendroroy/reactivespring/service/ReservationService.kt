package com.github.eendroroy.reactivespring.service

import com.github.eendroroy.reactivespring.model.Reservation
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * @author indrajit
 */
interface ReservationService {
    fun getReservation(id: String?): Mono<Reservation?>?
    fun createReservation(reservationMono: Mono<Reservation>): Mono<Reservation?>?
    fun updateReservation(id: String, reservationMono: Mono<Reservation>): Mono<Reservation?>?
    fun deleteReservation(id: String): Mono<Boolean?>?
    fun listAllReservations(): Flux<Reservation?>?
}
