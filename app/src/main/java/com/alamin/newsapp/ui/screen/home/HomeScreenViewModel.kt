package com.alamin.newsapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alamin.newsapp.domain.model.NewsRequest
import com.alamin.newsapp.domain.usecase.RefreshArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val refreshArticleUseCase: RefreshArticleUseCase) :
    ViewModel() {



    fun refreshArticle() {
        viewModelScope.launch {
            val newsRequest = NewsRequest("us", "business","a1f5a0a3f1dc4ca8afe397073b159464")
            refreshArticleUseCase.invoke(newsRequest)
        }

    }


}