package com.alamin.newsapp.ui.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.alamin.newsapp.domain.model.Article
import com.alamin.newsapp.domain.model.NewsCategory
import com.alamin.newsapp.ui.presentation.components.AnimatedProgressDialog
import com.alamin.newsapp.ui.presentation.components.buildImageRequest
import com.alamin.newsapp.ui.presentation.main.MainViewModel
import com.alamin.newsapp.core.utils.AppConstants
import com.alamin.newsapp.core.utils.extension.formatTime

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    toDetails: (Article) -> Unit
) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        mainViewModel.updateTitle("News Explorer")
    }


    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column {
            Text(
                text = "Welcome Joy ! Here is Your Breaking News",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(horizontal = AppConstants.APP_MARGIN.dp)
            )
            Spacer(modifier = Modifier.height(AppConstants.APP_MARGIN.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(AppConstants.APP_MARGIN.dp),
                modifier = Modifier.padding(horizontal = AppConstants.APP_MARGIN.dp)
            ) {
                items(NewsCategory.entries.toTypedArray()) { category ->
                    ElevatedButton(shape = MaterialTheme.shapes.medium, onClick = {
                        viewModel.refreshArticle(category)
                    }) {
                        Text(text = category.displayName)
                    }

                }
            }

            PullToRefreshBox(
                isRefreshing = uiState.isRefreshing,
                onRefresh = {
                    viewModel.updateState(uiState.copy(isRefreshing = true))
                },
                modifier = Modifier.fillMaxSize()
            ) {
                if (uiState.articles.isNotEmpty()) {
                    LazyColumn {
                        items(uiState.articles) { article ->
                            NewsItem(
                                article = article,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = AppConstants.APP_MARGIN.dp),
                                toDetails = toDetails
                            )
                            Spacer(modifier = Modifier.height(AppConstants.APP_MARGIN.dp))
                        }
                    }

                }

            }


        }

        if (uiState.isLoading) {
            AnimatedProgressDialog()
        }

        LaunchedEffect(uiState.isRefreshing) {
            if (uiState.isRefreshing) {
                viewModel.refreshArticle()
                viewModel.updateState(uiState.copy(isRefreshing = false))
            }
        }

        LaunchedEffect(uiState.message) {
            if (uiState.message != null) {
                Toast.makeText(context, uiState.message, Toast.LENGTH_SHORT).show()
                viewModel.updateState(uiState.copy(message = null))
            }
        }


    }

}

val articleModel = Article(
    title = "New Breakthrough in AI Ethics: Researchers Develop Explainable Algorithms",
    author = "Dr. Ava Sharma",
    content = "A team of researchers at the Global Institute oveloped a novel framework",
    description = "Researchers at GI.",
    publishedAt = "2023-10-27T14:30:00Z",
    url = "https://www.example.com/ai-ethics-breakthrough",
    urlToImage = "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/OSQUX4TIQRG3JNL65Y6W6RFWHY_size-normalized.jpg&w=1440"
)

@Composable
@Preview(showBackground = true)
fun NewsItem(
    article: Article = articleModel,
    modifier: Modifier = Modifier,
    toDetails: (Article) -> Unit = {}
) {
    ElevatedCard(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppConstants.APP_MARGIN.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                if (article.urlToImage != null) {
                    AsyncImage(
                        model = buildImageRequest(article.urlToImage),
                        contentDescription = article.title,
                        contentScale = ContentScale.Crop,
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppConstants.APP_MARGIN.dp / 2)
            ) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height((AppConstants.APP_MARGIN / 2).dp))
                Text(
                    text = "Author: ${article.author}",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = article.publishedAt.formatTime(),
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier.height(AppConstants.APP_MARGIN.dp)) // Spacer before button

            Button(
                onClick = { toDetails(article) },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = MaterialTheme.shapes.medium // Match card shape or customize
            ) {
                Text(
                    text = "See More",
                    style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.onPrimary)
                )
            }
        }
    }
}

