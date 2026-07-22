package com.aidarsarvartdinov.rustore.ui.appDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.aidarsarvartdinov.rustore.data.models.AppDetails
import com.aidarsarvartdinov.rustore.data.repository.DownloadProgress

@Composable
fun AppDetailsContent(
    app: AppDetails,
    downloadState: DownloadProgress?,
    onDownloadClick: () -> Unit,
    onCancelClick: () -> Unit,
    onRetryClick: () -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val screenWidthPx = LocalWindowInfo.current.containerSize.width
    val screenWidthDp = with(density) { screenWidthPx.toDp() }

    val screenshotHeight = if (screenWidthDp < 600.dp) 200.dp else 280.dp
    val screenshotWidth = if (screenWidthDp < 600.dp) 120.dp else 160.dp

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 24.dp,
                            bottomEnd = 24.dp
                        )
                    )
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = app.iconUrl,
                    contentDescription = app.name,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = app.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = app.developer,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))

                DownloadButton(
                    downloadState = downloadState,
                    onDownloadClick = onDownloadClick,
                    onCancelClick = onCancelClick,
                    onRetryClick = onRetryClick,
                    modifier = Modifier
                        .widthIn(max = 400.dp)
                        .fillMaxWidth()
                )
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStart = 24.dp,
                            topEnd = 24.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = app.category,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                    )
                    Text(
                        text = app.ageRating,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                if (app.screenshots.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))


                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(app.screenshots) { index, screenshotUrl ->
                            AsyncImage(
                                model = screenshotUrl,
                                contentDescription = "Скриншот",
                                modifier = Modifier
                                    .height(screenshotHeight)
                                    .width(screenshotWidth)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        navController.navigate("screenshots/${app.id}/$index")
                                    },
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = app.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}