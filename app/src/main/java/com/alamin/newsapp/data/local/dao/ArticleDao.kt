package com.alamin.newsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.alamin.newsapp.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("SELECT * FROM ArticleEntity")
    fun getArticles(): Flow<List<ArticleEntity>>

    @Query("DELETE FROM ArticleEntity")
    fun deleteAllArticles()


    @Transaction
    suspend fun deleteAndInsertArticles(articles: List<ArticleEntity>) {
        deleteAllArticles()
        insertArticles(articles)
    }



}