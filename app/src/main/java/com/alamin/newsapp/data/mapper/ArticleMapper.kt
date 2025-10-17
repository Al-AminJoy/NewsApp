package com.alamin.newsapp.data.mapper

import com.alamin.newsapp.data.local.entity.ArticleEntity
import com.alamin.newsapp.data.remote.model.ArticleDto
import com.alamin.newsapp.domain.model.Article

fun ArticleDto.toArticle(): Article {
    return Article(
        title = title ?: "Title Not Found",
        author = author ?: "Author Not Found",
        content = content ?: "Content not Available",
        description = description ?: "Description Not Found",
        publishedAt = publishedAt,
        url = url,
        urlToImage = urlToImage,
        )
}
fun ArticleDto.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        id = 0L,
        title = title ?: "",
        author = author ?: "Author Not Found",
        content = content ?: "Content not Available",
        description = description ?: "Description Not Found",
        publishedAt = publishedAt,
        url = url,
        urlToImage = urlToImage,
    )
}

fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        id = 0L,
        title = title,
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        url = url,
        urlToImage = urlToImage,
        )
}



fun ArticleEntity.toArticle(): Article {
    return Article(
        title = title,
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        url = url,
        urlToImage = urlToImage,
    )}