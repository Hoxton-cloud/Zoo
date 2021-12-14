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
    const val REQUEST_CODE_USERS_LIST = 1001
    const val REQUEST_CODE_USERS_DIRECTORY = 1002
    const val REQUEST_CODE_EMPLOYEES_LIST = 1003
    const val REQUEST_CODE_EMPLOYEES_DIRECTORY = 1004

    const val REQUEST_CODE_USERS_CREATE = 2001
    const val REQUEST_CODE_USERS_EDIT = 2002
}