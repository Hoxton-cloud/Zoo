package ru.zoo.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Species : Serializable {
    @SerializedName("id")
    var id = 0
    @SerializedName("title")
    var title = ""
    @SerializedName("climateZoneID")
    var climateZoneID = 0
    @SerializedName("typeID")
    var typeID = 0

    constructor()
    constructor(id: Int, title: String, climateZoneID: Int, typeID: Int) {
        this.id = id
        this.title = title
        this.climateZoneID = climateZoneID
        this.typeID = typeID
    }
}