package com.alamin.newsapp.ui.screen.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.alamin.newsapp.domain.model.Article
import com.alamin.newsapp.ui.navigation.Destination
import com.alamin.newsapp.ui.screen.main.MainViewModel

@Composable
fun DetailsScreen(article: Article, mainViewModel: MainViewModel, viewModel: DetailsScreenViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        mainViewModel.updateTitle("News Details")
    }
}