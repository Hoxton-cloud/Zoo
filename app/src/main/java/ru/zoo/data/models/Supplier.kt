package ru.zoo.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Supplier : Serializable {
    @SerializedName("id")
    var id = 0
    @SerializedName("title")
    var title = ""

    constructor()
    constructor(id: Int, title: String) {
        this.id = id
        this.title = title
    }
}