package com.alamin.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alamin.newsapp.data.local.dao.ArticleDao
import com.alamin.newsapp.data.local.entity.ArticleEntity
import com.alamin.newsapp.core.utils.AppConstants

@Database(entities = [ArticleEntity::class], version = AppConstants.DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun articleDao(): ArticleDao


}