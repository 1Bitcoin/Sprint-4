package ru.sber.datetime

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {

    val searchedZones = ZoneId.getAvailableZoneIds()
        .filter { TimeZone.getTimeZone(it).rawOffset % 3600000 != 0 }.toSet()

    return searchedZones
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {

    var searchedDays: MutableList<String> = mutableListOf()
    for (mounth in 1..12) {
        searchedDays.add(YearMonth.of(year, mounth).atEndOfMonth().dayOfWeek.toString())
    }

    return searchedDays
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int{
    var count = 0
    val calendar = Calendar.getInstance()

    for (mouth in 0..11) {
        calendar.set(year, mouth, 13)
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            count++
        }
    }

    return count
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM uuuu, HH:mm").withLocale(Locale.US)

    return formatter.format(dateTime)
}



