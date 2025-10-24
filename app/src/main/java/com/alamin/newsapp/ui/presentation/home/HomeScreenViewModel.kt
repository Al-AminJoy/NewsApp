package com.alamin.newsapp.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alamin.newsapp.core.network.ServerConstants
import com.alamin.newsapp.core.utils.Result
import com.alamin.newsapp.core.utils.extension.getMessage
import com.alamin.newsapp.domain.model.Article
import com.alamin.newsapp.domain.model.NewsCategory
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
import kotlinx.coroutines.flow.update
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


    val articles = articleUseCase.invoke()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    init {
        refreshArticle()
        observeArticles()
    }

    fun observeArticles(){
        viewModelScope.launch (IO){
            articles.collectLatest { latestArticles ->
                _uiState.update { it.copy(articles = latestArticles) }
            }
        }
    }

    fun refreshArticle(category: NewsCategory = NewsCategory.default()) {
        viewModelScope.launch(IO) {
            updateLoading(true)
            val newsRequest =
                NewsRequest("us", category.apiValue, ServerConstants.API_KEY )

            _uiState.update { state ->
                when (val result = refreshArticleUseCase(newsRequest)) {
                    is Result.Error -> {
                     val message =  result.exception.getMessage()
                        state.copy(
                            isLoading = false,
                            message = message
                        )
                    }

                    is Result.Success<*> -> {
                        state.copy(isLoading = false)
                    }
                }
            }


        }

    }

    fun updateState(uiState: UIState) {
        _uiState.update { uiState }
    }

    fun updateLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

}

data class UIState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val isRefreshing: Boolean = false,
    val articles: List<Article> = emptyList()
)