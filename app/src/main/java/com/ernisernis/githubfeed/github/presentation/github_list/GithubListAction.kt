package com.ernisernis.githubfeed.github.presentation.github_list

sealed interface GithubListAction {
    data object TestAction: GithubListAction
}