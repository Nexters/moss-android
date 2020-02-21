package com.nexters.moss.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    private fun getYesterday(date: Date): Date {
        val yesterday = Date()
        yesterday.time = date.time - 1000.toLong() * 60 * 60 * 24

        return yesterday
    }

    private fun getDayByDate(date: Date): String {
        return SimpleDateFormat("dd", Locale.KOREA).format(date)
    }

    private fun getTomorrow(date: Date): Date {
        return Date(date.time + (1000 * 60 * 60 * 24).toLong())
    }

    fun getFiveDays(): Array<String> {
        val days = Array(5) {Date()}

        val today = Date()

        days[0] = getYesterday(today)
        days[1] = today
        days[2] = getTomorrow(days[1])
        days[3] = getTomorrow(days[2])
        days[4] = getTomorrow(days[3])

        val resultDays = Array(5) {""}
        for (i in resultDays.indices) {
            resultDays[i] = getDayByDate(days[i])
        }

        return resultDays
    }

}