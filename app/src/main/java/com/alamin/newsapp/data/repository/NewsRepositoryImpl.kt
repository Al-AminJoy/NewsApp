package com.alamin.newsapp.data.repository

import com.alamin.newsapp.data.local.dao.ArticleDao
import com.alamin.newsapp.data.mapper.toArticle
import com.alamin.newsapp.data.mapper.toArticleEntity
import com.alamin.newsapp.data.remote.APIService
import com.alamin.newsapp.domain.model.Article
import com.alamin.newsapp.domain.model.NewsRequest
import com.alamin.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private val apiService: APIService,
    private val articleDao: ArticleDao
): NewsRepository {
    override fun getArticles(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshArticles(newsRequest: NewsRequest) {
        try {
            val response= apiService.getNews(newsRequest.country,newsRequest.category,newsRequest.apiKey)

            if (response.isSuccessful){
                response.body()?.let { newsResponseDto ->
                    articleDao.insertArticles(newsResponseDto.articles.map { it.toArticleEntity() })
                }
            }else{

            }
        }catch (e: Exception){

        }
    }

}