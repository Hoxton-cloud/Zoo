package ru.zoo.data

import java.io.File
import java.text.SimpleDateFormat

object Constants {
    var url: String? = "http://10.0.2.2:3000"
//    var url: String? = "http://localhost:3000"
    var isOnline = true
    var isOfflineCorrect = false
    var isOnlineCorrect = false
    var isBadOnlineCorrect = false
    var typeConnection = 0
    var country: String? = null
    var role: String? = ""
//    var currentUser: Partner? = null
    var lastIndexRecord = 1
    val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
    val inputDateFormat = SimpleDateFormat("yyyy-MM-dd")

    const val REQUEST_CODE = 0
    const val REQUEST_CODE_LIST = 1001
    const val REQUEST_CODE_DIRECTORY = 1002
    const val REQUEST_CODE_CREATE = 1003
    const val REQUEST_CODE_EDIT = 1004
}