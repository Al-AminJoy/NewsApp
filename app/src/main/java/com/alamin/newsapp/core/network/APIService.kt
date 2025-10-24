package com.alamin.newsapp.core.network

import com.alamin.newsapp.data.remote.dto.NewsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET(ApiEndPoints.ARTICLES)
    suspend fun getNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String): Response<NewsResponseDto>
}