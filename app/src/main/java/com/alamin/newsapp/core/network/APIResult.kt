package com.alamin.newsapp.core.network

sealed class APIResult< out T> {
    data class Success<out T>(val data: T) : APIResult<T>()
    data class Error(val exception: Exception) : APIResult<Nothing>()
}