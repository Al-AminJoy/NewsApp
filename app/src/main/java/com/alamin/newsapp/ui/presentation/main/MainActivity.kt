package com.alamin.newsapp.ui.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alamin.newsapp.ui.navigation.Destination
import com.alamin.newsapp.ui.navigation.NavGraph
import com.alamin.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentBackStack by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStack?.destination?.route
            val viewModel = hiltViewModel<MainViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            NewsAppTheme{
                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = uiState.title,
                                modifier = Modifier.Companion.fillMaxWidth(),
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Companion.Bold,
                                    textAlign = TextAlign.Companion.Center
                                )
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            titleContentColor = MaterialTheme.colorScheme.onBackground
                        ), actions = {
                            Row(verticalAlignment = Alignment.Companion.CenterVertically) {

                            }
                        }, navigationIcon = {
                            if (!(currentRoute ?: "").contains(
                                    Destination.Home.toString(),
                                    ignoreCase = true
                                ) && !(currentRoute ?: "").contains(
                                    Destination.Splash.toString(),
                                    ignoreCase = true
                                )
                            ) {
                                IconButton(onClick = {
                                    navController.navigateUp()
                                }) {
                                    Icon(
                                        Icons.Default.ArrowBackIosNew,
                                        contentDescription = "back",
                                        tint = MaterialTheme.colorScheme.onBackground
                                    )
                                }
                            }
                        })
                }, modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.Companion.padding(innerPadding)) {
                        App(navController, viewModel)
                    }
                }
            }

        }

    }

    @Composable
    fun App(navController: NavHostController, viewModel: MainViewModel) {
        NavGraph(navController = navController,viewModel ,startDestination = Destination.Splash)
    }
}