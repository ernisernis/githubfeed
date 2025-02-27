package com.ernisernis.githubfeed.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Dimens {

    // github_list
    val GithubListContainerPadding: Dp
        @Composable get() = 16.dp

    val GithubListContainerItemSpacing: Dp
        @Composable get() = 16.dp

    val GithubListItemPadding: Dp
        @Composable get() = 12.dp

    val GithubListItemSpacing: Dp
        @Composable get() = 8.dp

    // github_detail
    val GithubDetailContainerPadding: Dp
        @Composable get() = 12.dp

    val GithubDetailItemSpacing: Dp
        @Composable get() = 8.dp

    val GithubDetailItemPadding: Dp
        @Composable get() = 6.dp

    val GithubDetailRssItemSpacing: Dp
        @Composable get() = 4.dp
}