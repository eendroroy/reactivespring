package com.github.eendroroy.reactivespring.controller

import com.github.eendroroy.reactivespring.controller.ReservationResource.Companion.ROOM_V_1_RESERVATION
import com.github.eendroroy.reactivespring.model.Reservation
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.temporal.ChronoUnit


/**
 * @author indrajit
 */
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class ReservationResourceTest {

    @Autowired
    private val context: ApplicationContext? = null
    private var webTestClient: WebTestClient? = null
    private var reservation: Reservation? = null

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        webTestClient = WebTestClient
                .bindToApplicationContext(context!!)
                .build()
        reservation = Reservation(
                123L,
                LocalDate.now(),
                LocalDate.now().plus(10, ChronoUnit.DAYS),
                150
        )
    }

    @Test
    fun getAllReservations() {
        webTestClient!!.get()
                .uri(ROOM_V_1_RESERVATION)
                .exchange()
                .expectStatus().isOk
                .expectBodyList(Reservation::class.java)
    }

    @Test
    fun createReservation() {
        webTestClient!!.post()
                .uri(ROOM_V_1_RESERVATION)
                .body(Mono.just(reservation!!), Reservation::class.java as Class<*>)
                .exchange()
                .expectStatus().isOk
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Reservation::class.java)
    }
}