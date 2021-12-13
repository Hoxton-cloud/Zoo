package ru.zoo.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MedicalExamination : Serializable {
    @SerializedName("id")
    var id = 0
    @SerializedName("date")
    var date = ""
    @SerializedName("animalID")
    var animalID = 0
    @SerializedName("diagnosisID")
    var diagnosisID = 0

    constructor()
    constructor(id: Int, date: String, animalID: Int, diagnosisID: Int) {
        this.id = id
        this.date = date
        this.animalID = animalID
        this.diagnosisID = diagnosisID
    }
}