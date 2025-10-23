package com.alamin.newsapp.data.repository

import com.alamin.newsapp.data.local.dao.ArticleDao
import com.alamin.newsapp.data.mapper.toArticle
import com.alamin.newsapp.data.mapper.toArticleEntity
import com.alamin.newsapp.data.remote.APIService
import com.alamin.newsapp.domain.model.Article
import com.alamin.newsapp.domain.model.NewsRequest
import com.alamin.newsapp.domain.repository.NewsRepository
import com.alamin.newsapp.core.utils.Result
import com.alamin.newsapp.core.utils.exception.ServerException
import com.alamin.newsapp.core.utils.extension.getException
import com.alamin.newsapp.core.utils.extension.getSpecificException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private val apiService: APIService,
    private val articleDao: ArticleDao
): NewsRepository {
    override fun getArticles(): Flow<List<Article>> {
        return articleDao.getArticles().map { articleList -> articleList.map { it.toArticle() } }
    }

    override suspend fun refreshArticles(newsRequest: NewsRequest): Result<List<Article>> {
        return try {
            val response= apiService.getNews(newsRequest.country,
                newsRequest.category,
                newsRequest.apiKey)

            if (response.isSuccessful && response.body() != null){
                val newsResponseDto = response.body()!!
                articleDao.deleteAndInsertArticles(newsResponseDto.articles.map { it.toArticleEntity() })
                Result.Success(newsResponseDto.articles.map { it.toArticle() })
            }else{
                Result.Error(ServerException(response.getException()))
            }
        }catch (e: Exception){
            Result.Error(Exception(e.getSpecificException()))
        }
    }

}