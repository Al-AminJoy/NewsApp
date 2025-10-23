package com.alamin.newsapp.utils

import android.util.Log

private const val TAG = "Logger"

object Logger {
    fun log(tag: String = TAG, message: String?){
        if (AppConstants.ENABLE_LOG){
            Log.d(tag, "$message")
        }
    }
}