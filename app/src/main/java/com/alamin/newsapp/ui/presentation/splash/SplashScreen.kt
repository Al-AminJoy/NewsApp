package com.alamin.newsapp.ui.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.alamin.newsapp.ui.presentation.components.SplashLogo

@Composable
fun SplashScreen(viewModel: SplashViewModel = hiltViewModel(),onSplashFinished: () -> Unit) {

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.showSplash) {
        if (!state.showSplash) {
            viewModel.updateShowSplash(false)
            onSplashFinished()
        }
    }

    Box (contentAlignment = Alignment.Center,modifier = Modifier.fillMaxSize()){
        SplashLogo()
    }


}