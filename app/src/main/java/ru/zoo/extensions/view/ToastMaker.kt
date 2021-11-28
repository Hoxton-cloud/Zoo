package ru.zoo.extensions.view

import android.content.Context
import android.widget.*
import ru.zoo.R


class ToastMaker {
    fun toastErrorConnect(context: Context) {
        Toast.makeText(context, R.string.connect_error, Toast.LENGTH_SHORT).show()
    }
}