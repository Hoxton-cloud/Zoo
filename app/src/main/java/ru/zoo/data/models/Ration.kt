package ru.zoo.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Ration : Serializable {
    @SerializedName("id")
    var id = 0
    @SerializedName("feedID")
    var feedID = 0
    @SerializedName("animalID")
    var animalID = 0
    @SerializedName("time")
    var time = ""
    @SerializedName("mass")
    var mass = ""

    constructor()
    constructor(id: Int, feedID: Int, animalID: Int, time: String, mass: String) {
        this.id = id
        this.feedID = feedID
        this.animalID = animalID
        this.time = time
        this.mass = mass
    }

}