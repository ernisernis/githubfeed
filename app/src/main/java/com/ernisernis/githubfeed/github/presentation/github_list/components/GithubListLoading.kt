package com.ernisernis.githubfeed.github.presentation.github_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ernisernis.githubfeed.ui.theme.GithubFeedTheme

@Composable
fun GithubListLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun GithubListLoadingPreview() {
    GithubFeedTheme {
        GithubListLoading(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}