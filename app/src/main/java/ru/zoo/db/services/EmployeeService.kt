package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EmployeeService {
    @GET("/mobileGetEmployeesList")
    fun getEmployeesList(
        @Query("token") token: String?, @Query("userID") userID: String?
    ): Call<ResponseBody>

    @GET("/mobileGetEmployeeByID")
    fun getEmployeeByID(
        @Query("token") token: String?, @Query("userID") userID: Int?, @Query("employeeID") employeeID: Int?
    ): Call<ResponseBody>

    @POST("/mobileEditEmployee/add")
    fun addEmployee(
        @Query("positionID") positionID: Int?,
        @Query("firstName") firstName: String?,
        @Query("lastName") lastName: String?,
        @Query("patronymic") patronymic: String?,
        @Query("phoneNumber") phoneNumber: String?
    ): Call<ResponseBody>

    @POST("/mobileEditEmployee/update")
    fun editEmployee(
        @Query("token") token: String?,
        @Query("userID") userID: Int?,
        @Query("positionID") positionID: Int?,
        @Query("firstName") firstName: String?,
        @Query("lastName") lastName: String?,
        @Query("patronymic") patronymic: String?,
        @Query("phoneNumber") phoneNumber: String?,
        @Query("id") id: Int?
    ): Call<ResponseBody>
}