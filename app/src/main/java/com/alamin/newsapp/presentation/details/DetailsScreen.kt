package com.alamin.newsapp.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.alamin.newsapp.domain.model.Article
import com.alamin.newsapp.presentation.components.buildImageRequest
import com.alamin.newsapp.presentation.main.MainViewModel
import com.alamin.newsapp.core.utils.AppConstants
import com.alamin.newsapp.core.utils.extension.formatTime

@Composable
fun DetailsScreen(
    article: Article,
    mainViewModel: MainViewModel,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        mainViewModel.updateTitle("News Details")
    }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(all = AppConstants.APP_MARGIN.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                if (article.urlToImage != null) {
                    AsyncImage(
                        model = buildImageRequest(article.urlToImage),
                        contentScale = ContentScale.Crop,
                        contentDescription = article.title,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.BrokenImage,
                        contentDescription = "No image available",
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(AppConstants.APP_MARGIN.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(horizontal = AppConstants.APP_MARGIN.dp)
            ) {
                Text("By : ${article.author}", style = MaterialTheme.typography.labelMedium)
                Text(article.publishedAt.formatTime(), style = MaterialTheme.typography.labelMedium)
            }

            Spacer(modifier = Modifier.height(AppConstants.APP_MARGIN.dp))
            HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(horizontal = AppConstants.APP_MARGIN.dp))
            Spacer(modifier = Modifier.height(AppConstants.APP_MARGIN.dp))

            Text(article.title, style = MaterialTheme.typography.titleLarge,modifier = Modifier.fillMaxWidth().padding(horizontal = AppConstants.APP_MARGIN.dp))

            Spacer(modifier = Modifier.height(AppConstants.APP_MARGIN.dp))

            Text(article.content.substringBefore("[+").trim(), style = MaterialTheme.typography.labelMedium,modifier = Modifier.fillMaxWidth().padding(horizontal = AppConstants.APP_MARGIN.dp))

        }
    }
}