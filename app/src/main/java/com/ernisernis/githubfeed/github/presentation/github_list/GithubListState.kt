package com.ernisernis.githubfeed.github.presentation.github_list

import com.ernisernis.githubfeed.github.domain.FeedsType

data class GithubListState(
    val loading: Boolean = true,
    val urlLink: String? = null,
    val feedsType: FeedsType = FeedsType.TIMELINE,
    val timelineState: TimelineState = TimelineState(),
    val linkUserState: LinkUserState = LinkUserState(),
    val repoDiscussionsState: RepositoryDiscussionsState = RepositoryDiscussionsState(),
    val repoDiscussionsCategoryState: RepositoryDiscussionsCategoryState = RepositoryDiscussionsCategoryState(),
    val securityAdvisoriesState: SecurityAdvisoriesState = SecurityAdvisoriesState()
)

data class TimelineState(
    val title: String = "Timeline",
    val href: String? = null,
    val type: String? = null,
    val showSection: Boolean = false,
)

data class LinkUserState(
    val title: String = "User",
    val input: String = "",
    val inputLabel: String = "user",
    val href: String? = null,
    val type: String? = null,
    val showSection: Boolean = false,
)

data class RepositoryDiscussionsState(
    val title: String = "Discussions",
    val input1: String = "",
    val input1Label: String = "user",
    val input2: String = "",
    val input2Label: String = "repo",
    val href: String? = null,
    val type: String? = null,
    val showSection: Boolean = false,
)

data class RepositoryDiscussionsCategoryState(
    val title: String = "Discussions Category",
    val input1: String = "",
    val input1Label: String = "user",
    val input2: String = "",
    val input2Label: String = "repo",
    val input3: String = "",
    val input3Label: String = "category",
    val href: String? = null,
    val type: String? = null,
    val showSection: Boolean = false,
)

data class SecurityAdvisoriesState(
    val title: String = "Security Advisories",
    val href: String? = null,
    val type: String? = null,
    val showSection: Boolean = false,
)