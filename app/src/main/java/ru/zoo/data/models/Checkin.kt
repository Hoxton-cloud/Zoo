package ru.zoo.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Checkin : Serializable {
    @SerializedName("id")
    var id = 0
    @SerializedName("date")
    var date = ""
    @SerializedName("animalID")
    var animalID = 0
    @SerializedName("employeeID")
    var employeeID = 0
    @SerializedName("aviaryID")
    var aviaryID = 0

    constructor()
    constructor(id: Int, date: String, animalID: Int, employeeID: Int, aviaryID: Int) {
        this.id = id
        this.date = date
        this.animalID = animalID
        this.employeeID = employeeID
        this.aviaryID = aviaryID
    }
}