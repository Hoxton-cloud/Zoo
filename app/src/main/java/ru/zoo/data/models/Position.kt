package ru.zoo.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Position : Serializable {
    @SerializedName("id")
    var id = 0
    @SerializedName("title")
    var title = ""
    @SerializedName("salary")
    var salary = ""

    constructor()
    constructor(id: Int, title: String, salary: String) {
        this.id = id
        this.title = title
        this.salary = salary
    }
}