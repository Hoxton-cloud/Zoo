package ru.zoo.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Animal : Serializable {
    @SerializedName("id")
    var id = 0
    @SerializedName("name")
    var name = ""
    @SerializedName("sex")
    var sex = ""
    @SerializedName("speciesID")
    var speciesID = 0

    constructor()
    constructor(id: Int, name: String, sex: String, speciesID: Int) {
        this.id = id
        this.name = name
        this.sex = sex
        this.speciesID = speciesID
    }

}