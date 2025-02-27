package com.ernisernis.githubfeed.github.presentation.github_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ernisernis.githubfeed.ui.theme.GithubFeedTheme

@Composable
fun FeedItem(
    modifier: Modifier = Modifier,
    title: String,
    urlPathLink: String?,
    onClick: () -> Unit,
    inputContent: @Composable () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12))
            .background(MaterialTheme.colorScheme.secondary)
            .clickable { onClick() }
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondary
        )

        Text(
            text = urlPathLink ?: "",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )

        inputContent()
    }
}

@Preview
@Composable
fun FeedItemPreview() {
    GithubFeedTheme {
        FeedItem(
            modifier = Modifier,
            title = "Test Title",
            urlPathLink = "https://github.com/timeline",
            onClick = {}
        )
    }
}