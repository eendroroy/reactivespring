package com.github.eendroroy.reactivespring.controller

import com.github.eendroroy.reactivespring.model.Reservation
import com.github.eendroroy.reactivespring.service.ReservationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * @author indrajit
 */
@RestController
@RequestMapping(ReservationResource.ROOM_V_1_RESERVATION)
@CrossOrigin
class ReservationResource @Autowired constructor(private val reservationService: ReservationService) {
    @GetMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getReservationById(@PathVariable id: String): Mono<Reservation?>? {
        return reservationService.getReservation(id)
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllReservationById(): Flux<Reservation?>? {
        return reservationService.listAllReservations()
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createReservation(@RequestBody reservation: Mono<Reservation>): Mono<Reservation?>? {
        return reservationService.createReservation(reservation)
    }

    @PutMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateReservation(@PathVariable id: String, @RequestBody reservation: Mono<Reservation>): Mono<Reservation?>? {
        return reservationService.updateReservation(id, reservation)
    }

    @DeleteMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteReservation(@PathVariable id: String): Mono<Boolean?>? {
        return reservationService.deleteReservation(id)
    }

    companion object {
        const val ROOM_V_1_RESERVATION = "/room/v1/reservation"
    }
}