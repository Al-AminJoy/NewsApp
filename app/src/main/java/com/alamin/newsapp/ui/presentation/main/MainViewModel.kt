package com.alamin.newsapp.ui.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {

        viewModelScope.launch {
        }
    }

    fun updateState(state: UiState) {
        _uiState.update {  state }
    }

    fun updateTitle(title: String) {
        _uiState.update { it.copy(title = title) }
    }
}

data class UiState(
    val isLoading: Boolean = false,
    val title:String = "",
)