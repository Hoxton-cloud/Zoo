package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DiagnosisService {
    @GET("/mobileGetDiagnosesList")
    fun getDiagnosesList(
        @Query("token") token: String?, @Query("userID") userID: String?
    ): Call<ResponseBody>

    @GET("/mobileGetDiagnosisByID")
    fun getDiagnosisByID(
        @Query("token") token: String?, @Query("userID") userID: String?, @Query("diagnosisID") diagnosisID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditDiagnosis/add")
    fun addDiagnosis(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("title") title: String?
    ): Call<ResponseBody>

    @POST("/mobileEditDiagnosis/update")
    fun editDiagnosis(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("title") title: String?,
        @Query("id") id: String?
    ): Call<ResponseBody>
}