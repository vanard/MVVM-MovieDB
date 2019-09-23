package com.vanard.ovotask.utils

import java.text.SimpleDateFormat
import java.util.*

object FormatDate {

    const val DATE_FORMAT = "yyyy-MM-dd"
    const val DATE_FORMAT_OUTPUT = "dd MMMM yyyy"

    private fun stringToDate(strDate: String, formatInput: String): Date {
        return if (strDate.isNullOrEmpty()) {
            Calendar.getInstance().time
        } else {
            SimpleDateFormat(formatInput, Locale.getDefault()).parse(strDate)
        }
    }

    private fun dateToString(date: Date, formatOutput: String): String {
        return SimpleDateFormat(formatOutput, Locale.getDefault()).format(date)
    }

    fun reformatStringDate(stringDate: String, formatInput: String, formatOutput: String): String {
        return dateToString(
            stringToDate(
                stringDate,
                formatInput
            ), formatOutput
        )
    }
}