package com.alamin.newsapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val title: String,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String?,
    val url: String?,
    val urlToImage: String?
)
