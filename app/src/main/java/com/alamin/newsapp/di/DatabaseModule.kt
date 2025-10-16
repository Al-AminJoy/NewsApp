package com.alamin.newsapp.di

import android.content.Context
import androidx.room.Room
import com.alamin.newsapp.data.local.AppDatabase
import com.alamin.newsapp.data.local.dao.ArticleDao
import com.alamin.newsapp.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder<AppDatabase>(appContext, AppConstants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()}

    @Provides
    @Singleton
    fun provideArticleDao(appDatabase: AppDatabase): ArticleDao = appDatabase.articleDao()


}