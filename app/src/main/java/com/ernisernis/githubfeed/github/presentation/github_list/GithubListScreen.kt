package com.ernisernis.githubfeed.github.presentation.github_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ernisernis.githubfeed.github.data.dto.FeedsDto
import com.ernisernis.githubfeed.github.presentation.github_list.components.FeedItem
import com.ernisernis.githubfeed.ui.theme.GithubFeedTheme


@Composable
fun GithubListScreenRoot(
    viewModel: GithubListViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    GithubListScreen(
        state = state,
        modifier = Modifier
            .statusBarsPadding()
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        onAction = { action ->
            when (action)  {
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun GithubListScreen(
    state: GithubListState,
    modifier: Modifier = Modifier,
    onAction: (GithubListAction) -> Unit,
) {
    AnimatedVisibility(
        visible = state.feedsUi != null
    ) {
        Column(
            modifier = modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeedItem(
                title = state.timelineState.title,
                urlPathLink = state.feedsUi?.timelineUrl ?: "",
                onClick = {}
            )
            FeedItem(
                title = state.linkUserState.title,
                urlPathLink = state.feedsUi?.userUrl ?: "",
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
fun GithubListScreenPreview() {
    val state = GithubListState()
    GithubFeedTheme {
        GithubListScreen(
            state = state,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            onAction = {}
        )
    }
}