package ru.zoo.data

import java.io.File
import java.text.SimpleDateFormat

object Constants {
    var url: String? = null
    var isOnline = true
    var isOfflineCorrect = false
    var isOnlineCorrect = false
    var isBadOnlineCorrect = false
    var typeConnection = 0
    var country: String? = null
//    var currentUser: Partner? = null
    var lastIndexRecord = 1
    val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
    val inputDateFormat = SimpleDateFormat("yyyy-MM-dd")

    const val REQUEST_CODE = 0
}