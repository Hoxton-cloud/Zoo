package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MedicalExaminationService {
    @GET("/mobileGetMedicalExaminationsList")
    fun getMedicalExaminationsList(
        @Query("token") token: String?, @Query("userID") userID: String?
    ): Call<ResponseBody>

    @GET("/mobileGetMedicalExaminationByID")
    fun getMedicalExaminationByID(
        @Query("token") token: String?, @Query("userID") userID: String?, @Query("medicalExaminationID") medicalExaminationID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditMedicalExamination/add")
    fun addMedicalExamination(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("date") date: String?,
        @Query("animalID") animalID: String?,
        @Query("diagnosisID") diagnosisID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditMedicalExamination/update")
    fun editMedicalExamination(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("date") date: String?,
        @Query("animalID") animalID: String?,
        @Query("diagnosisID") diagnosisID: String?,
        @Query("id") id: String?
    ): Call<ResponseBody>
}