package com.alamin.newsapp.core.utils.extension

import com.alamin.newsapp.core.utils.AppException

fun AppException.getMessage() = when (this) {
    is AppException.AuthException -> message
    is AppException.NetworkException -> message
    is AppException.ServerException -> message
    is AppException.OthersException -> message
}