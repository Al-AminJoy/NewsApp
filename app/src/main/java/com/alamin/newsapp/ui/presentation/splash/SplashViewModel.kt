package com.alamin.newsapp.ui.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            updateShowSplash(false)
        }
    }

    fun updateShowSplash(showSplash: Boolean) {
        _uiState.value = _uiState.value.copy(showSplash = showSplash)
    }
}

data class UIState(
    val showSplash: Boolean = true
)