package com.alamin.newsapp.core.utils.extension

import com.alamin.newsapp.core.utils.AppException
import com.alamin.newsapp.core.utils.Logger
import java.net.ConnectException
import java.net.UnknownHostException
import java.nio.channels.UnresolvedAddressException

private const val TAG = "ExceptionExt"

fun Exception.getSpecificException(): AppException{
    Logger.log(TAG, "getSpecificException: ${this.message}")
   return when(this){
        is UnresolvedAddressException -> AppException.NetworkException(message = "Please, Check Your Internet Connection")
        is UnknownHostException, is ConnectException -> AppException.NetworkException(message = "Unable to Communicate With Server")
        else -> AppException.OthersException(message = "Something Went Wrong")
    }
}