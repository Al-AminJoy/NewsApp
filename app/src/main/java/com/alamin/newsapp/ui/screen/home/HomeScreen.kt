package com.alamin.newsapp.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alamin.newsapp.R
import com.alamin.newsapp.domain.model.Article
import com.alamin.newsapp.ui.theme.Blue
import com.alamin.newsapp.ui.theme.Red
import com.alamin.newsapp.utils.AppConstants
import com.alamin.newsapp.utils.extension.formatTime

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel(), toDetails: (Article) -> Unit) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column {
            Text(
                text = "Welcome Joy ! Here is Your Breaking News",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(horizontal = AppConstants.APP_MARGIN.dp)
            )
            Spacer(modifier = Modifier.height(AppConstants.APP_MARGIN.dp))
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

}

val articleModel = Article(
    title = "New Breakthrough in AI Ethics: Researchers Develop Explainable Algorithms",
    author = "Dr. Ava Sharma",
    content = "A team of researchers at the Global Institute of Technology has announced a significant breakthrough in the field of AI ethics. They have successfully developed a novel framework that allows artificial intelligence algorithms to explain their decision-making processes in human-understandable terms. This development is crucial for building trust in AI systems used in critical sectors like healthcare and finance. The framework, dubbed 'ClarityAI,' uses a combination of natural language processing and causal inference to generate concise and relevant explanations for complex AI outputs. Initial tests show a 90% human comprehension rate of the explanations...",
    description = "Researchers at GIT unveil 'ClarityAI,' a new framework for explainable AI, enhancing transparency and trust in critical applications.",
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
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(article.urlToImage)
                            .crossfade(true)
                            .error(R.drawable.ic_launcher_background)
                            .build(),
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
                Spacer(modifier = Modifier.height(4.dp))
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

            // 3. Details Button at the bottom
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

