package com.alamin.newsapp.ui.screen.details

import androidx.lifecycle.ViewModel
import com.alamin.newsapp.domain.usecase.ArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val articleUseCase: ArticleUseCase
) : ViewModel() {

}