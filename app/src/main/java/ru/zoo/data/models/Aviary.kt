package ru.zoo.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Aviary : Serializable {
    @SerializedName("id")
    var id = 0
    @SerializedName("number")
    var number = ""
    @SerializedName("typeID")
    var typeID = 0

    constructor()
    constructor(id: Int, number: String, typeID: Int) {
        this.id = id
        this.number = number
        this.typeID = typeID
    }
}