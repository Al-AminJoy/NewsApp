package com.alamin.newsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.alamin.newsapp.domain.model.Article
import com.alamin.newsapp.ui.screen.details.DetailsScreen
import com.alamin.newsapp.ui.screen.home.HomeScreen
import kotlinx.serialization.json.Json

@Composable
fun NavGraph(navController: NavHostController, startDestination: Destination) {
        NavHost(navController = navController, startDestination = startDestination){
            composable<Destination.Home> {
                HomeScreen{
                    val article = Json.encodeToString(it)
                    navController.navigate(Destination.Details(article))
                }
            }

            composable<Destination.Details> {
                val route = it.toRoute<Destination.Details>()
                val article = Json.decodeFromString<Article>(route.article)
                DetailsScreen(article)
            }
        }
}