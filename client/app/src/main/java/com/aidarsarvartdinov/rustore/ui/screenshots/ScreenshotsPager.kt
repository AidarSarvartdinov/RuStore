package com.aidarsarvartdinov.rustore.ui.screenshots

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@Composable
fun ScreenshotsPager(
    screenshots: List<String>,
    initialIndex: Int
) {
    val pagerState = rememberPagerState (
        initialPage = initialIndex.coerceIn(0, screenshots.size - 1)
    ) { screenshots.size }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),

    ) {
//        screenshot
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            page ->
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = screenshots[page],
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight(0.9f)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Fit
                    )
                }

        }

//        miniatures
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(screenshots) { index, screenshot ->
                val isSelected = index == pagerState.currentPage
                val alpha = if (isSelected) 1.0f else 0.5f

                AsyncImage(
                    model = screenshot,
                    contentDescription = "Миниатюра",
                    modifier = Modifier
                        .height(64.dp)
                        .width(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                    contentScale = ContentScale.Crop,
                    alpha = alpha
                )
            }
        }
    }
}