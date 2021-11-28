package ru.zoo.extensions.util

import android.util.Base64
import java.io.UnsupportedEncodingException

fun String.toSave() : String{
    return this.replace("\"[", "[").replace("]\"", "]").replace("\\\"", "\"").replace(
        "\\\\\"",
        "\\u0022"
    ).removeSurrounding("\"")
}
