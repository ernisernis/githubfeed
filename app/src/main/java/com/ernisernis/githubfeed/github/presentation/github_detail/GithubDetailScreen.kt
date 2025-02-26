package com.ernisernis.githubfeed.github.presentation.github_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
fun GithubDetailScreenRoot(
    viewModel: GithubDetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

   GithubDetailScreen(
       state = state,
       onAction = { action ->
           when (action)  {
               GithubDetailAction.TestAction -> TODO()
           }
           viewModel.onAction(action)
       }
   )
}

@Composable
fun GithubDetailScreen(
    modifier: Modifier = Modifier,
    state: GithubDetailState,
    onAction: (GithubDetailAction) -> Unit,
) {
    Box(
        modifier = modifier
            .background(Color.Red)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = state.urlLink
        )
    }

}

@Preview
@Composable
fun GithubDetailScreenPreview() {
    GithubFeedTheme {
        GithubDetailScreen(
            state = GithubDetailState(
                urlLink = "https://helloworld.org"
            ),
            onAction = {}
        )
    }
}