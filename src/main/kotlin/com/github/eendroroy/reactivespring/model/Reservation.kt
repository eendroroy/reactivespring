package com.github.eendroroy.reactivespring.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

/**
 * @author indrajit
 */
@Document
data class Reservation(
        var roomNumber: Long? = null,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) var checkIn: LocalDate? = null,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) var checkOut: LocalDate? = null,
        var price: Int? = null,
        @Id var id: String? = null
)