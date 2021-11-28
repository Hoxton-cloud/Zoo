package ru.zoo.data.models

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Simplest : Serializable {
    constructor()
    constructor(id: String, name: String) {
        this.id = id
        this.name = name
    }

    @SerializedName("id")
    var id = ""

    @SerializedName("name")
    var name = ""
}