package com.aliyabano.vendorapp

import android.util.Log
import com.aliyabano.vendorapp.Model.VendorUpdateResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.Response

fun <T> result(call: suspend () -> Response<T>): Flow<ApiResponse<T?>> = flow {

    emit(ApiResponse.Loading)
    try {
        val c = call()
        c.let {
            if (it.isSuccessful) {
                emit(ApiResponse.Success(it.body()))
            } else {
                c.errorBody()?.let { error ->
                    error.close()
                    emit(ApiResponse.Failure(error.toString()))
                }
            }
        }

    } catch (t: Throwable) {
        t.printStackTrace()
        emit(ApiResponse.Failure("${t.message}"))
    }

}