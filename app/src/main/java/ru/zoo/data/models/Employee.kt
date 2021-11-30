package ru.zoo.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Employee : Serializable {
    @SerializedName("id")
    var id = 0
    @SerializedName("positionID")
    var positionID = 0
    @SerializedName("firstName")
    var firstName = ""
    @SerializedName("lastName")
    var lastName = ""
    @SerializedName("patronymic")
    var patronymic = ""
    @SerializedName("phoneNumber")
    var phoneNumber = ""

    constructor()
    constructor(
        id: Int,
        positionID: Int,
        firstName: String,
        lastName: String,
        patronymic: String,
        phoneNumber: String
    ) {
        this.id = id
        this.positionID = positionID
        this.firstName = firstName
        this.lastName = lastName
        this.patronymic = patronymic
        this.phoneNumber = phoneNumber
    }

}