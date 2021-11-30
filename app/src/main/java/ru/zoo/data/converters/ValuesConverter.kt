package ru.zoo.data.converters

import ru.zoo.data.Constants.inputDateFormat
import ru.zoo.data.Constants.simpleDateFormat

fun nullChecker(value: String): String {
    return if (value == "null") {
        ""
    } else {
        value
    }
}

fun dateParser(date: String): String {
    if (date == "") {
        return ""
    }
    try {
        val dateInput = inputDateFormat.parse(date)
        return simpleDateFormat.format(dateInput)
    } catch (e: Exception) {
    }
    return date
}