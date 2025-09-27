package com.alamin.newsapp.utils.extension

import android.util.Log
import com.alamin.newsapp.utils.exception.CustomException
import com.alamin.newsapp.utils.exception.NetworkException
import java.net.ConnectException
import java.net.UnknownHostException
import java.nio.channels.UnresolvedAddressException

private const val TAG = "ExceptionExt"

fun Exception.getErrorMessage():String{
    return this.message?:"Something Went Wrong"
}

fun Exception.getSpecificException():Exception{
    Log.d(TAG, "getSpecificException: ${this.message}")
   return when(this){
        is UnresolvedAddressException -> NetworkException(message = "Please, Check Your Internet Connection")
        is UnknownHostException, is ConnectException -> NetworkException(message = "Unable to Communicate With Server")
        else -> CustomException(message = "Something Went Wrong")
    }
}