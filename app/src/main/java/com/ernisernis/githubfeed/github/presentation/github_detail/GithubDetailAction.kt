package com.ernisernis.githubfeed.github.presentation.github_detail


sealed interface GithubDetailAction {
    data object TestAction: GithubDetailAction
}