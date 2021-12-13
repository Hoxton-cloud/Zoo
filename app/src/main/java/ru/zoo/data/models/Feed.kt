package ru.zoo.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Feed : Serializable {
    @SerializedName("id")
    var id = 0
    @SerializedName("title")
    var title = ""
    @SerializedName("typeID")
    var typeID = 0

    constructor()
    constructor(id: Int, title: String, typeID: Int) {
        this.id = id
        this.title = title
        this.typeID = typeID
    }
}