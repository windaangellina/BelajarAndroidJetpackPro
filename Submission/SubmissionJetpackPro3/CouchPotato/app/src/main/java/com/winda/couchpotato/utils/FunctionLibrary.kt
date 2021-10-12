package com.winda.couchpotato.utils

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object FunctionLibrary {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val zoneId: ZoneId = ZoneId.systemDefault()

    private fun getDateFromString(dateString: String) : LocalDate {
        return LocalDate.parse(dateString, formatter)
    }

    fun getDateAsLong(dateString: String) : Long{
        val date = getDateFromString(dateString)
        return date.atStartOfDay(zoneId).toEpochSecond()
    }

    fun getLocalDateTimeFromLong(epochTime : Long) : LocalDateTime {
        // JVM representation of a millisecond epoch absolute instant
        val instant = Instant.ofEpochMilli(epochTime)

        // Adding the timezone information to be able to format it (change accordingly)
        return LocalDateTime.ofInstant(instant, zoneId)
    }

}