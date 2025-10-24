package com.alamin.newsapp.core.utils

sealed class AppException(message: String){
    data class AuthException(val message:String = "Authentication Failed") : AppException(message)
    data class NetworkException(val message:String = "Please, Check Your Internet Connection") : AppException(message)
    class ServerException(val message:String):AppException(message)
    class OthersException(val message: String) : AppException(message)
}

