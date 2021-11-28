package ru.zoo.extensions.util

import android.app.Activity
import android.content.Intent
import android.net.Uri

fun dialPhoneNumber(activity:Activity,phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    activity.startActivity(intent)
}