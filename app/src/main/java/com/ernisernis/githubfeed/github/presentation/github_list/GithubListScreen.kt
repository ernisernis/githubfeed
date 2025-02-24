package com.ernisernis.githubfeed.github.presentation.github_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    Box(
        modifier = modifier
            .background(Color.Red)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello from Github list screen!"
        )
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
                .background(Color.Red)
                .fillMaxSize(),
            onAction = {}
        )
    }
}