package com.alamin.newsapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alamin.newsapp.domain.model.Article
import com.alamin.newsapp.domain.model.NewsRequest
import com.alamin.newsapp.domain.usecase.ArticleUseCase
import com.alamin.newsapp.domain.usecase.RefreshArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val refreshArticleUseCase: RefreshArticleUseCase,
    private val articleUseCase: ArticleUseCase
) :
    ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    init {
       refreshArticle()
    }

    val articles = articleUseCase.invoke()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    init {
        viewModelScope.launch(IO) {
            articles.collectLatest {  latestArticles ->
                _uiState.value = _uiState.value.copy(articles = latestArticles)
            }
        }
    }

    private fun refreshArticle() {
        viewModelScope.launch (IO){
            val newsRequest = NewsRequest("us", "business", "a1f5a0a3f1dc4ca8afe397073b159464")
            refreshArticleUseCase.invoke(newsRequest)
        }

    }

}

data class UIState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val exception: Exception? = null,
    val articles: List<Article> = arrayListOf()
)