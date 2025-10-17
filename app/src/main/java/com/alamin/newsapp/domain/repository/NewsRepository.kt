package com.alamin.newsapp.domain.repository

import com.alamin.newsapp.domain.model.Article
import com.alamin.newsapp.domain.model.NewsRequest
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

     fun getArticles(): Flow<List<Article>>

    suspend fun refreshArticles(newsRequest: NewsRequest)

}