package com.alamin.newsapp.core.utils

sealed class AppException(message: String){
    class AuthException(val message:String = "Authentication Failed"): AppException(message)
    class ServerException(val message:String):AppException(message)
    class NetworkException(val message: String) : AppException(message)
    class OthersException(val message: String) : AppException(message)
}

