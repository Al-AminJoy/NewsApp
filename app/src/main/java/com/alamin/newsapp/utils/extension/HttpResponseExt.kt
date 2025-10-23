package com.alamin.newsapp.utils.extension

import com.alamin.newsapp.data.remote.model.ApiErrorDto
import com.alamin.newsapp.utils.Logger
import retrofit2.Response


private const val TAG = "HttpResponseExt"
fun <T> Response<T>.getException(): String {
    return try {
        val apiError = this.body() as ApiErrorDto
        Logger.log(TAG, "getException: $apiError")
        apiError.message?: "Something went wrong, please try again later"
    } catch (ex: Exception) {
        Logger.log(TAG, "getException: Exception $ex")
        "Something went wrong, please try again later"
    }
}
