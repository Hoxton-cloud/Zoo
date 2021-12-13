package ru.zoo.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Offspring : Serializable {
    @SerializedName("id")
    var id = 0
    @SerializedName("amount")
    var amount = ""
    @SerializedName("animalID")
    var animalID = 0

    constructor()
    constructor(id: Int, amount: String, animalID: Int) {
        this.id = id
        this.amount = amount
        this.animalID = animalID
    }
}