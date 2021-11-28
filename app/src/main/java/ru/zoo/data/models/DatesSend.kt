package ru.zoo.data.models

import com.google.gson.annotations.SerializedName

class DatesSend {
    @SerializedName("endDate")
    var endDate: String? = null
    @SerializedName("beginDate")
    var beginDate: String? = null
    @SerializedName("noDate")
    var noDate : Boolean? = null
    fun sendDate(date:String) : DatesSend{

        val datesSend : DatesSend = DatesSend()
        when {
            date.contains("Без") -> {
                datesSend.noDate = true
                datesSend.endDate = ""
                datesSend.beginDate = ""
            }
            date.contains("С ") -> {
                datesSend.beginDate = date.substring(2,date.length)
                datesSend.endDate = ""
            }
            date.contains("До ") -> {
                datesSend.beginDate = ""
                datesSend.endDate = date.substring(3,date.length)
            }
            date.contains(" - ") -> {
                datesSend.beginDate = date.substring(0,10)
                datesSend.endDate = date.substring(13,date.length)
            }
            else -> {
                datesSend.beginDate = date
                datesSend.endDate = date
            }
        }

        return datesSend
    }
}
class DatesSend2{
    @SerializedName("endDate")
    var endDate: String? = null
    @SerializedName("beginDate")
    var beginDate: String? = null
    fun sendDate2(date:String) : DatesSend2{

            val datesSend2 : DatesSend2 = DatesSend2()
            when {
                date.contains("С ") -> {
                    datesSend2.beginDate = date.substring(2,date.length)
                    datesSend2.endDate = ""
                }
                date.contains("До ") -> {
                    datesSend2.beginDate = ""
                    datesSend2.endDate = date.substring(3,date.length)
                }
                date.contains(" - ") -> {
                    datesSend2.beginDate = date.substring(0,10)
                    datesSend2.endDate = date.substring(13,date.length)
                }
                else -> {
                    datesSend2.beginDate = date
                    datesSend2.endDate = date
                }
            }

        return datesSend2
    }
}

