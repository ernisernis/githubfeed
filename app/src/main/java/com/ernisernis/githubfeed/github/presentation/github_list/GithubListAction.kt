package com.ernisernis.githubfeed.github.presentation.github_list

sealed interface GithubListAction {
    data class OnLinkUserInputChange(val text: String): GithubListAction
    data class OnRepoDiscussionsInput1Change(val text: String): GithubListAction
    data class OnRepoDiscussionsInput2Change(val text: String): GithubListAction
    data class OnRepoDiscussionsCategoryInput1Change(val text: String): GithubListAction
    data class OnRepoDiscussionsCategoryInput2Change(val text: String): GithubListAction
    data class OnRepoDiscussionsCategoryInput3Change(val text: String): GithubListAction
}