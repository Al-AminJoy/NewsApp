package com.alamin.newsapp.presentation.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun updateState(state: UiState) {
        _uiState.update {  state }
    }

    fun updateTitle(title: String) {
        _uiState.update { it.copy(title = title) }
    }
}

data class UiState(
    val isLoading: Boolean = false,
    val title:String = "Home"
)