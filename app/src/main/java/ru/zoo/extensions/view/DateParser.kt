package ru.zoo.extensions.view

import androidx.core.graphics.convertTo
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
fun getTitlePeriod(title: String, title2:String,status:Int,prefix:String): String {
    var date = ""
    if(status <3){
        date = prefix
        when (title.substring(3, 5)) {
            "01" -> date += title.substring(0, 2) + " Января "
            "02" -> date += title.substring(0, 2) + " Февраля "
            "03" -> date += title.substring(0, 2) + " Марта "
            "04" -> date += title.substring(0, 2) + " Апреля "
            "05" -> date += title.substring(0, 2) + " Мая "
            "06" -> date += title.substring(0, 2) + " Июня "
            "07" -> date += title.substring(0, 2) + " Июля "
            "08" -> date += title.substring(0, 2) + " Августа "
            "09" -> date += title.substring(0, 2) + " Сентября "
            "10" -> date += title.substring(0, 2) + " Октября "
            "11" -> date += title.substring(0, 2) + " Ноября "
            "12" -> date += title.substring(0, 2) + " Декабря "
        }
    }else{
        when (title.substring(3, 5)) {
            "01" -> date = title.substring(0, 2) + " Января "
            "02" -> date = title.substring(0, 2) + " Февраля "
            "03" -> date = title.substring(0, 2) + " Марта "
            "04" -> date = title.substring(0, 2) + " Апреля "
            "05" -> date = title.substring(0, 2) + " Мая "
            "06" -> date = title.substring(0, 2) + " Июня "
            "07" -> date = title.substring(0, 2) + " Июля "
            "08" -> date = title.substring(0, 2) + " Августа "
            "09" -> date = title.substring(0, 2) + " Сентября "
            "10" -> date = title.substring(0, 2) + " Октября "
            "11" -> date = title.substring(0, 2) + " Ноября "
            "12" -> date = title.substring(0, 2) + " Декабря "
        }
        date += prefix
        when (title2.substring(3, 5)) {
            "01" -> date += title2.substring(0, 2) + " Января "
            "02" -> date += title2.substring(0, 2) + " Февраля "
            "03" -> date += title2.substring(0, 2) + " Марта "
            "04" -> date += title2.substring(0, 2) + " Апреля "
            "05" -> date += title2.substring(0, 2) + " Мая "
            "06" -> date += title2.substring(0, 2) + " Июня "
            "07" -> date += title2.substring(0, 2) + " Июля "
            "08" -> date += title2.substring(0, 2) + " Августа "
            "09" -> date += title2.substring(0, 2) + " Сентября "
            "10" -> date += title2.substring(0, 2) + " Октября "
            "11" -> date += title2.substring(0, 2) + " Ноября "
            "12" -> date += title2.substring(0, 2) + " Декабря "
        }
    }


    return date
}
fun getTitleDate(title: String, dayOfWeek: Int): String {
    var date = ""
    when (title.substring(3, 5)) {
        "01" -> date = title.substring(0, 2) + " Января, "
        "02" -> date = title.substring(0, 2) + " Февраля, "
        "03" -> date = title.substring(0, 2) + " Марта, "
        "04" -> date = title.substring(0, 2) + " Апреля, "
        "05" -> date = title.substring(0, 2) + " Мая, "
        "06" -> date = title.substring(0, 2) + " Июня, "
        "07" -> date = title.substring(0, 2) + " Июля, "
        "08" -> date = title.substring(0, 2) + " Августа, "
        "09" -> date = title.substring(0, 2) + " Сентября, "
        "10" -> date = title.substring(0, 2) + " Октября, "
        "11" -> date = title.substring(0, 2) + " Ноября, "
        "12" -> date = title.substring(0, 2) + " Декабря, "
    }
    when (dayOfWeek) {
        1 -> date += "вс."
        2 -> date += "пн."
        3 -> date += "вт."
        4 -> date += "ср."
        5 -> date += "чт."
        6 -> date += "пт."
        7 -> date += "сб."
    }
    return date
}
fun getTitleDatePeriod(title: String): String{
    var date = ""
    when (title.substring(3, 5)) {
        "01" -> date = title.substring(0, 2) + " Января "
        "02" -> date = title.substring(0, 2) + " Февраля "
        "03" -> date = title.substring(0, 2) + " Марта "
        "04" -> date = title.substring(0, 2) + " Апреля "
        "05" -> date = title.substring(0, 2) + " Мая "
        "06" -> date = title.substring(0, 2) + " Июня "
        "07" -> date = title.substring(0, 2) + " Июля "
        "08" -> date = title.substring(0, 2) + " Августа "
        "09" -> date = title.substring(0, 2) + " Сентября "
        "10" -> date = title.substring(0, 2) + " Октября "
        "11" -> date = title.substring(0, 2) + " Ноября "
        "12" -> date = title.substring(0, 2) + " Декабря "
    }
    return date
}
fun convertDateToLong(date: String): Long {
    val df = SimpleDateFormat("yyyy-MM-dd")
    return df.parse(date).time
}
fun convertLongToTitle(title: String): String {
    var month = ""
    when (title.substring(0, 2)) {
        "01" -> month = "Январь " + title.substring(2, title.length)
        "02" -> month = "Февраль " + title.substring(2, title.length)
        "03" -> month = "Март " + title.substring(2, title.length)
        "04" -> month = "Апрель " + title.substring(2, title.length)
        "05" -> month = "Май " + title.substring(2, title.length)
        "06" -> month = "Июнь " + title.substring(2, title.length)
        "07" -> month = "Июль " + title.substring(2, title.length)
        "08" -> month = "Август " + title.substring(2, title.length)
        "09" -> month = "Сентябрь " + title.substring(2, title.length)
        "10" -> month = "Октябрь " + title.substring(2, title.length)
        "11" -> month = "Ноябрь " + title.substring(2, title.length)
        "12" -> month = "Декабрь " + title.substring(2, title.length)
    }
    return month
}
fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("MM yyyy")
    return format.format(date)
}
fun dateToString(date:Date) : String{
    var dateStr =""
    try {
        dateStr = simpleDateFormat.format(
            Objects.requireNonNull(
                date
            )
        )
    }catch(e: ParseException) {
        e.printStackTrace()
    }
    return dateStr
}
fun dateParse(dates: ArrayList<String>) : String {
    var dateString = ""
    var dateString2 = ""
    var day = 0
    var prefix = ""
    var currentDate: Date? = null
    var currentDate5: Date? = null
    var calendarExt = Calendar.getInstance()
    var c = Calendar.getInstance()
    if (dates.size > 0) {
        when {
            dates[0].contains("С ") -> {
                currentDate = simpleDateFormat.parse(
                    dates[0].substring(
                        2,
                        dates[0].length
                    )
                )
                dateString = simpleDateFormat.format(currentDate!!)


                calendarExt.time = currentDate
                c.time = currentDate
                c.add(Calendar.DATE, 1000)
                currentDate5 = c.time

                prefix = "С "
                return getTitlePeriod(
                    dateString,
                    dateString2,
                    prefix = prefix, status = 1
                )


            }
            dates[0].contains("До ") -> {
                currentDate = simpleDateFormat.parse(
                    dates[0].substring(
                        3,
                        dates[0].length
                    )
                )
                dateString = simpleDateFormat.format(currentDate!!)

                calendarExt.time = currentDate
                c.time = currentDate
                c.add(Calendar.DATE, -1000)
                currentDate5 = c.time
                prefix = "До "
                return getTitlePeriod(
                    dateString,
                    dateString2,
                    prefix = prefix, status = 2
                )

            }
            dates[0].contains(" - ") -> {
                currentDate = simpleDateFormat.parse(dates[0].substring(0, 10))
                dateString = simpleDateFormat.format(currentDate!!)
                currentDate5 = simpleDateFormat.parse(
                    dates[0].substring(
                        13,
                        dates[0].length
                    )
                )
                dateString2 = simpleDateFormat.format(currentDate5!!)
                calendarExt.time = currentDate5
                c.time = currentDate5
                calendarExt.time = currentDate
                c.time = currentDate
                prefix = " - "
                return getTitlePeriod(
                    dateString,
                    dateString2,
                    prefix = prefix, status = 3
                )

            }
            dates[0].contains("Без даты") -> {
                // currentDate = simpleDateFormat.parse(dates[0])
                // dateString = simpleDateFormat.format(currentDate!!)
                // calendarExt.time = currentDate
                // currentDate5 = currentDate
                return "Без даты"
            }
            else -> {
                currentDate = simpleDateFormat.parse(dates[0])
                dateString = simpleDateFormat.format(currentDate!!)
                calendarExt.time = currentDate
                currentDate5 = currentDate
                return getTitleDatePeriod(dateString)
            }
        }
    } else if (dates.size > 1) {
        return "Выбрано множество дат"
    } else {
        dateString = simpleDateFormat.format(Date())
        calendarExt.time = Date()
        day = calendarExt.get(Calendar.DAY_OF_WEEK)
        return getTitleDate(dateString, day)
    }


}
fun convertIntToWeekShortTitle(dayInt: Int): String {
    var shortDayTitle = ""
    when (dayInt) {
        0 -> shortDayTitle = "вс"
        1 -> shortDayTitle = "пн"
        2 -> shortDayTitle = "вт"
        3 -> shortDayTitle = "ср"
        4 -> shortDayTitle = "чт"
        5 -> shortDayTitle = "пт"
        6 -> shortDayTitle = "сб"
    }
    return shortDayTitle
}
fun convertIntToWeekTitle(dayInt: Int): String {
    var dayTitle = ""
    when (dayInt) {
        0 -> dayTitle = "Воскресенье"
        1 -> dayTitle = "Понедельник"
        2 -> dayTitle = "Вторник"
        3 -> dayTitle = "Среда"
        4 -> dayTitle = "Четверг"
        5 -> dayTitle = "Пятница"
        6 -> dayTitle = "Суббота"
    }
    return dayTitle
}
fun convertIntToWeekTitle(hourInt: Int, minuteInt: Int): String {
    var timeTitle = ""
    timeTitle = if (hourInt < 10) "0$hourInt:" else "$hourInt:"
    timeTitle += if (minuteInt < 10) "0$minuteInt" else "$minuteInt"
    return timeTitle
}
