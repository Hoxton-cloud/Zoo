package ru.zoo.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Feeding : Serializable {
    @SerializedName("id")
    var id = 0
    @SerializedName("employeeID")
    var employeeID = 0
    @SerializedName("animalID")
    var animalID = 0
    @SerializedName("rationID")
    var rationID = 0

    constructor()
    constructor(id: Int, employeeID: Int, animalID: Int, rationID: Int) {
        this.id = id
        this.employeeID = employeeID
        this.animalID = animalID
        this.rationID = rationID
    }
}