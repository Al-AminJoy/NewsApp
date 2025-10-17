package com.alamin.newsapp.domain.usecase

import com.alamin.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class ArticleUseCase @Inject constructor(private val newsRepository: NewsRepository) {

  operator fun invoke() = newsRepository.getArticles()

}