package com.ernisernis.githubfeed.github.presentation.github_list

import com.ernisernis.githubfeed.github.presentation.models.FeedsUi

data class GithubListState(
    val loading: Boolean = true,
    val feedsUi: FeedsUi? = null,
    val timelineState: TimelineState = TimelineState(),
    val linkUserState: LinkUserState = LinkUserState(),
    val repoDiscussionsState: RepositoryDiscussionsState = RepositoryDiscussionsState(),
    val repositoryDiscussionsCategoryState: RepositoryDiscussionsCategoryState = RepositoryDiscussionsCategoryState(),
    val securityAdvisoriesState: SecurityAdvisoriesState = SecurityAdvisoriesState()
)

data class TimelineState(
    val title: String = "Timeline",
)

data class LinkUserState(
    val title: String = "User",
    val input: String = "",
    val inputLabel: String = "user",
)

data class RepositoryDiscussionsState(
    val title: String = "Discussions",
    val input1: String = "",
    val input2: String = "",
)

data class RepositoryDiscussionsCategoryState(
    val title: String = "Discussions Category",
    val input1: String = "",
    val input2: String = "",
    val input3: String = "",
)

data class SecurityAdvisoriesState(
    val title: String = "Security Advisories"
)