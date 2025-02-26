package com.ernisernis.githubfeed.github.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ernisernis.githubfeed.ui.theme.GithubFeedTheme

@Composable
fun GithubLoadingIndicator(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = color,
        )
    }
}

@Preview
@Composable
fun GithubLoadingIndicatorPreview() {
    GithubFeedTheme {
        GithubLoadingIndicator(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.Red
        )
    }
}
