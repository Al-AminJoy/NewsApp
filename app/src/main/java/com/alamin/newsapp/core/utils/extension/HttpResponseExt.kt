package com.alamin.newsapp.core.utils.extension

import com.alamin.newsapp.core.utils.Logger
import com.alamin.newsapp.data.remote.dto.ApiErrorDto
import retrofit2.Response


private const val TAG = "HttpResponseExt"
fun <T> Response<T>.getException(): String {
    return try {

        if (this.body() != null){
            val apiError = this.body() as ApiErrorDto
            Logger.log(TAG, "getException: $apiError")
            apiError.message?: "Something went wrong, please try again later"
        }else{
            "Response body is null"
        }


    } catch (ex: Exception) {
        Logger.log(TAG, "getException: Exception $ex")
        "Something went wrong, please try again later"
    }
}
