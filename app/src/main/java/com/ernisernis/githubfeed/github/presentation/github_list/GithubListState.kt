package com.ernisernis.githubfeed.github.presentation.github_list

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import com.ernisernis.githubfeed.R
import com.ernisernis.githubfeed.github.domain.FeedsType

@Stable
data class GithubListState(
    val loading: Boolean = true,
    val urlLink: String? = null,
    val feedsType: FeedsType = FeedsType.TIMELINE,
    @StringRes val errorMessage: Int? = null,
    val timelineState: TimelineState = TimelineState(),
    val linkUserState: LinkUserState = LinkUserState(),
    val repoDiscussionsState: RepositoryDiscussionsState = RepositoryDiscussionsState(),
    val repoDiscussionsCategoryState: RepositoryDiscussionsCategoryState = RepositoryDiscussionsCategoryState(),
    val securityAdvisoriesState: SecurityAdvisoriesState = SecurityAdvisoriesState()
)

@Stable
data class TimelineState(
    @StringRes val title: Int = R.string.title_timeline,
    val href: String? = null,
    val type: String? = null,
    val showSection: Boolean = false,
)

@Stable
data class LinkUserState(
    @StringRes val title: Int = R.string.title_user,
    val input: String = "",
    @StringRes val inputLabel: Int = R.string.user_label,
    @StringRes val inputError: Int? = null,
    val href: String? = null,
    val type: String? = null,
    val showSection: Boolean = false,
)

@Stable
data class RepositoryDiscussionsState(
    @StringRes val title: Int = R.string.title_discussions,
    val input1: String = "",
    @StringRes val input1Label: Int = R.string.user_label,
    @StringRes val input1Error: Int? = null,
    val input2: String = "",
    @StringRes val input2Label: Int = R.string.repo_label,
    @StringRes val input2Error: Int? = null,
    val href: String? = null,
    val type: String? = null,
    val showSection: Boolean = false,
)

@Stable
data class RepositoryDiscussionsCategoryState(
    @StringRes val title: Int = R.string.title_discussions_category,
    val input1: String = "",
    @StringRes val input1Label: Int = R.string.user_label,
    @StringRes val input1Error: Int? = null,
    val input2: String = "",
    @StringRes val input2Label: Int = R.string.repo_label,
    @StringRes val input2Error: Int? = null,
    val input3: String = "",
    @StringRes val input3Label: Int = R.string.category_label,
    @StringRes val input3Error: Int? = null,
    val href: String? = null,
    val type: String? = null,
    val showSection: Boolean = false,
)

@Stable
data class SecurityAdvisoriesState(
    @StringRes val title: Int = R.string.title_security_advisories,
    val href: String? = null,
    val type: String? = null,
    val showSection: Boolean = false,
)