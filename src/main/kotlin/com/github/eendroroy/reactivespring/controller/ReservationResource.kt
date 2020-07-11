package com.github.eendroroy.reactivespring.controller

import com.github.eendroroy.reactivespring.controller.ReservationResource
import com.github.eendroroy.reactivespring.model.Reservation
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

/**
 * @author indrajit
 */
@RestController
@RequestMapping(ReservationResource.ROOM_V_1_RESERVATION)
@CrossOrigin
class ReservationResource {
    @GetMapping(path = ["{roomId}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun reservationById(@PathVariable roomId: String): Mono<Reservation> {
        return Mono.just(Reservation())
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createReservation(@RequestBody reservation: Reservation): Mono<Reservation> {
        return Mono.just(Reservation())
    }

    @PutMapping(path = ["{roomId}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateReservation(@PathVariable roomId: String, @RequestBody reservation: Reservation): Mono<Reservation> {
        return Mono.just(Reservation())
    }

    @DeleteMapping(path = ["{roomId}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteReservation(@PathVariable roomId: String): Mono<Boolean> {
        return Mono.just(true)
    }

    companion object {
        const val ROOM_V_1_RESERVATION = "/room/v1/reservation/"
    }
}