package ru.zoo.db.queries

import android.content.Context
import android.util.Log
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.zoo.data.Preferences
import ru.zoo.data.converters.EmployeesJSONConverter
import ru.zoo.data.models.Employee
import ru.zoo.db.getQueryClient
import ru.zoo.db.services.EmployeeService
import ru.zoo.extensions.view.ToastMaker

class EmployeesQueries {
    fun getEmployeesFromServer(
        onStart: () -> Unit,
        onFinish: (ArrayList<Employee>) -> Unit,
        context: Context
    ) {
        onStart.invoke()
        var employees = ArrayList<Employee>()
        val employeeService =
            getQueryClient(context).create(EmployeeService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = employeeService.getEmployeesList(token, userId.toString())
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        val jsonArray = JSONArray(remoteResponse)
                        employees = EmployeesJSONConverter()
                            .deserializeList(jsonArray)
                        onFinish.invoke(employees)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                } else {
                    Log.e("error", response.errorBody()!!.string())
                }

            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("fail", t.message + t.localizedMessage)
                ToastMaker().toastErrorConnect(context)
                onFinish.invoke(employees)
                call.cancel()
            }
        })
    }

    fun getEmployeeByIDFromServer(
        onStart: () -> Unit,
        onFinish: (ArrayList<Employee>) -> Unit,
        context: Context,
        soughtEmployeeID: Int
    ) {
        onStart.invoke()
        var employees = ArrayList<Employee>()
        val employeeService =
            getQueryClient(context).create(EmployeeService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 99999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = employeeService.getEmployeeByID(token, userId, soughtEmployeeID)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        val jsonArray = JSONArray(remoteResponse)
                        employees = EmployeesJSONConverter()
                            .deserializeList(jsonArray)
                        onFinish.invoke(employees)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        onFinish.invoke(employees)
                    }

                } else {
                    Log.e("error", response.errorBody()!!.string())
                    onFinish.invoke(employees)
                }

            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("fail", t.message + t.localizedMessage)
                ToastMaker().toastErrorConnect(context)
                onFinish.invoke(employees)
                call.cancel()
            }
        })
    }

    fun editEmployeeByID(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        context: Context,
        employee: Employee
    ) {
        onStart.invoke()
        val employeeService =
            getQueryClient(context).create(EmployeeService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 9999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = employeeService.editEmployee(
            token,
            userId,
            employee.positionID,
            employee.firstName,
            employee.lastName,
            employee.patronymic,
            employee.phoneNumber,
            employee.id)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        onFinish.invoke()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                } else {
                    Log.e("error", response.errorBody()!!.string())
                }

            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("fail", t.message + t.localizedMessage)
                ToastMaker().toastErrorConnect(context)
                onFinish.invoke()
                call.cancel()
            }
        })
    }

    fun addEmployee(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        context: Context,
        employee: Employee
    ) {
        onStart.invoke()
        val employeeService =
            getQueryClient(context).create(EmployeeService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = employeeService.addEmployee(
            employee.positionID,
            employee.firstName,
            employee.lastName,
            employee.patronymic,
            employee.phoneNumber
        )
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        onFinish.invoke()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                } else {
                    Log.e("error", response.errorBody()!!.string())
                }

            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("fail", t.message + t.localizedMessage)
                ToastMaker().toastErrorConnect(context)
                onFinish.invoke()
                call.cancel()
            }
        })
    }
}