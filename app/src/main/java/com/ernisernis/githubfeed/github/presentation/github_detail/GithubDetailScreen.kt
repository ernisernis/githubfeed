package com.ernisernis.githubfeed.github.presentation.github_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.ernisernis.githubfeed.R
import com.ernisernis.githubfeed.core.presentation.util.Dimens
import com.ernisernis.githubfeed.core.presentation.util.getFormattedDateTime
import com.ernisernis.githubfeed.github.presentation.components.GithubLoadingIndicator
import com.ernisernis.githubfeed.github.presentation.github_detail.components.RssListItem
import com.ernisernis.githubfeed.ui.theme.GithubFeedTheme
import com.prof18.rssparser.model.RssChannel
import com.prof18.rssparser.model.RssItem


@Composable
fun GithubDetailScreenRoot(
    viewModel: GithubDetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.loading) {
        GithubLoadingIndicator(
            modifier = modifier,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
        )
    } else {
        GithubDetailScreen(
            state = state,
            modifier = modifier
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .systemBarsPadding()
                .fillMaxSize(),
            onAction = viewModel::onAction,
        )
    }

}

@Composable
fun GithubDetailScreen(
    modifier: Modifier = Modifier,
    state: GithubDetailState,
    onAction: (GithubDetailAction) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimens.GithubDetailItemSpacing),
        contentPadding = PaddingValues(Dimens.GithubDetailContainerPadding)
    ) {
        state.rssChannel?.title?.let {
            item {
                Text(
                    text = it,
                    modifier = Modifier
                        .animateItem(),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
        state.rssChannel?.link?.let {
            item {
                Text(
                    text = it,
                    modifier = Modifier
                        .animateItem(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
        state.rssChannel?.description?.let {
            item {
                Text(
                    text = it,
                    modifier = Modifier
                        .animateItem(),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
        state.rssChannel?.image?.let { image ->
            item {
                AsyncImage(
                    model = image.url,
                    contentDescription = null
                )
            }
        }
        state.rssChannel?.lastBuildDate?.let {
            item {
                Text(
                    text = stringResource(R.string.last_build_date, it.getFormattedDateTime()),
                    modifier = Modifier
                        .animateItem(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
        state.rssChannel?.updatePeriod?.let {
            item {
                Text(
                    text = stringResource(R.string.update_period, it),
                    modifier = Modifier
                        .animateItem(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }

        state.rssChannel?.items?.let { items ->
            itemsIndexed(
                items = items,
                key = { index, item ->
                    item.guid ?: index
                }
            ) { _, item ->
                RssListItem(
                    modifier = Modifier,
                    item = item,
                )
            }
        }
    }
}

@Preview
@Composable
fun GithubDetailScreenPreview() {
    GithubFeedTheme {
        val state = GithubDetailState(
            rssChannel = defaultRssChannel
        )
        GithubDetailScreen(
            state = state,
            onAction = {}
        )
    }
}

internal val defaultRssItem = RssItem(
    guid = "12345",
    title = "Sample Item 1",
    author = "John Doe",
    link = "https://www.example.com/item1",
    pubDate = "2025-02-26T12:00:00Z",
    description = "This is the description for item 1.",
    content = "This is the content of item 1.",
    image = "https://www.example.com/item1.jpg",
    audio = null,
    video = null,
    sourceName = "Sample Source 1",
    sourceUrl = "https://www.example.com/source1",
    categories = listOf("Technology", "News"),
    itunesItemData = null,
    commentsUrl = "https://www.example.com/item1/comments",
    youtubeItemData = null,
    rawEnclosure = null
)

internal val defaultRssItem2 = RssItem(
    guid = "123456",
    title = "Sample Item 1",
    author = "John Doe",
    link = "https://www.example.com/item1",
    pubDate = "2025-02-26T12:00:00Z",
    description = "This is the description for item 1.",
    content = "This is the content of item 1.",
    image = "https://www.example.com/item1.jpg",
    audio = null,
    video = null,
    sourceName = "Sample Source 1",
    sourceUrl = "https://www.example.com/source1",
    categories = listOf("Technology", "News"),
    itunesItemData = null,
    commentsUrl = "https://www.example.com/item1/comments",
    youtubeItemData = null,
    rawEnclosure = null
)

internal val defaultRssChannel = RssChannel(
    title = "Sample Channel",
    link = "https://www.example.com",
    description = "This is a sample RSS channel description.",
    lastBuildDate = "2025-02-26T12:00:00Z",
    updatePeriod = "daily",
    items = listOf(defaultRssItem, defaultRssItem2),
    image = null,
    itunesChannelData = null,
    youtubeChannelData = null,
)