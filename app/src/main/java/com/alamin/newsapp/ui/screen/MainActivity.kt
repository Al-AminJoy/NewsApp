package com.alamin.newsapp.ui.screen

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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.rememberNavController
import com.alamin.newsapp.ui.navigation.Destination
import com.alamin.newsapp.ui.navigation.NavGraph
import com.alamin.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme (darkTheme = true){
                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "News Explorer", modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, textAlign = TextAlign.Center))
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            titleContentColor = MaterialTheme.colorScheme.onBackground
                        )
                    , actions = {
                            Row(verticalAlignment = Alignment.CenterVertically){
                                IconButton(onClick = {}) {
                                    Icon(Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.onBackground)
                                }
                            }
                        })
                }, modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        App()
                    }
                }
            }
        }
    }

    @Composable
    fun App() {
        val navController = rememberNavController()
        NavGraph(navController = navController, startDestination = Destination.Home)
    }
}