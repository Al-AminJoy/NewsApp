package com.alamin.newsapp.domain.usecase

import com.alamin.newsapp.domain.model.NewsRequest
import com.alamin.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class RefreshArticleUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(newsRequest: NewsRequest) = newsRepository.refreshArticles(newsRequest)

}