package com.alamin.newsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.alamin.newsapp.domain.model.Article
import com.alamin.newsapp.ui.presentation.details.DetailsScreen
import com.alamin.newsapp.ui.presentation.home.HomeScreen
import com.alamin.newsapp.ui.presentation.main.MainViewModel
import kotlinx.serialization.json.Json

@Composable
fun NavGraph(navController: NavHostController,mainViewModel: MainViewModel, startDestination: Destination) {
        NavHost(navController = navController, startDestination = startDestination){
            composable<Destination.Home> {
                HomeScreen(mainViewModel = mainViewModel){
                    val article = Json.encodeToString(it)
                    navController.navigate(Destination.Details(article))
                }
            }

            composable<Destination.Details> {
                val route = it.toRoute<Destination.Details>()
                val article = Json.decodeFromString<Article>(route.article)
                DetailsScreen(article, mainViewModel = mainViewModel)
            }
        }
}