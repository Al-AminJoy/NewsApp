package com.alamin.newsapp.domain.model

data class NewsRequest(
    val country: String,
    val category: String,
    val apiKey: String
)
