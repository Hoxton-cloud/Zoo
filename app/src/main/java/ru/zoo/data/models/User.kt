package ru.zoo.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User : Serializable {
    @SerializedName("id")
    var id = 0
    @SerializedName("username")
    var username = ""
    @SerializedName("password")
    var password = ""
    @SerializedName("token")
    var token = ""
    @SerializedName("employeeID")
    var employeeID = 0
    @SerializedName("role")
    var role = ""

    constructor()
    constructor(
        id: Int,
        username: String,
        password: String,
        token: String,
        employeeID: Int,
        role: String
    ) {
        this.id = id
        this.username = username
        this.password = password
        this.token = token
        this.employeeID = employeeID
        this.role = role
    }
}