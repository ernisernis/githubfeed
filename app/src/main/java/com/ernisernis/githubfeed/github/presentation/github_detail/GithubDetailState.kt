package com.ernisernis.githubfeed.github.presentation.github_detail

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import com.prof18.rssparser.model.RssChannel

@Stable
data class GithubDetailState(
    val loading: Boolean = true,
    @StringRes val errorMessage: Int? = null,
    val rssChannel: RssChannel? = null,
)
