package com.ernisernis.githubfeed.github.presentation.github_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ernisernis.githubfeed.core.domain.util.onError
import com.ernisernis.githubfeed.core.domain.util.onSuccess
import com.ernisernis.githubfeed.core.presentation.util.formatUrlWithReplacements
import com.ernisernis.githubfeed.core.presentation.util.toStringRes
import com.ernisernis.githubfeed.core.presentation.util.validateContentType
import com.ernisernis.githubfeed.github.domain.FeedsType
import com.ernisernis.githubfeed.github.domain.GithubRepository
import com.ernisernis.githubfeed.github.presentation.models.ValidateCategory
import com.ernisernis.githubfeed.github.presentation.models.ValidateRepository
import com.ernisernis.githubfeed.github.presentation.models.ValidateUsername
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GithubListViewModel @Inject constructor(
    private val githubRepository: GithubRepository,
    private val validateUsername: ValidateUsername,
    private val validateRepository: ValidateRepository,
    private val validateCategory: ValidateCategory,
) : ViewModel() {
    // TODO: Add OneTimeWhileSubscribed helper function to emit only once after active subscription in order to reduce API calls
    private val _state = MutableStateFlow(GithubListState())
    val state = _state
        .onStart {
            getGithubFeeds()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = GithubListState()
        )


    fun onAction(action: GithubListAction) {
        when (action) {
            is GithubListAction.OnLinkUserInputChange -> {
                _state.update { it.copy(
                    linkUserState = state.value.linkUserState.copy(
                        input = action.text
                    )
                ) }
            }
            is GithubListAction.OnRepoDiscussionsInput1Change -> {
                _state.update { it.copy(
                    repoDiscussionsState = state.value.repoDiscussionsState.copy(
                        input1 = action.text
                    )
                ) }
            }
            is GithubListAction.OnRepoDiscussionsInput2Change -> {
                _state.update { it.copy(
                    repoDiscussionsState = state.value.repoDiscussionsState.copy(
                        input2 = action.text
                    )
                ) }
            }
            is GithubListAction.OnRepoDiscussionsCategoryInput1Change -> {
                _state.update { it.copy(
                    repoDiscussionsCategoryState = state.value.repoDiscussionsCategoryState.copy(
                        input1 = action.text
                    )
                ) }
            }
            is GithubListAction.OnRepoDiscussionsCategoryInput2Change -> {
                _state.update { it.copy(
                    repoDiscussionsCategoryState = state.value.repoDiscussionsCategoryState.copy(
                        input2 = action.text
                    )
                ) }
            }
            is GithubListAction.OnRepoDiscussionsCategoryInput3Change -> {
                _state.update { it.copy(
                    repoDiscussionsCategoryState = state.value.repoDiscussionsCategoryState.copy(
                        input3 = action.text
                    )
                ) }
            }

            is GithubListAction.OnFeedsClick -> {
                when (action.type) {
                    FeedsType.TIMELINE -> handleTimelineClick()
                    FeedsType.USER -> handleUserClick()
                    FeedsType.REPO_DISCUSSIONS -> handleRepoDiscussionsClick()
                    FeedsType.REPO_DISCUSSIONS_CATEGORY -> handleRepoDiscussionsCategoryClick()
                    FeedsType.SECURITY_ADVISORIES -> handleSecurityAdvisories()
                }
            }
        }
    }

    private fun handleTimelineClick() {
        _state.update { it.copy(
            urlLink = state.value.timelineState.href,
            feedsType = FeedsType.TIMELINE,
        ) }
    }

    private fun handleUserClick() {
        val usernameResult = validateUsername.execute(state.value.linkUserState.input)

        if (!usernameResult.successful) {
            _state.update { it.copy(
                linkUserState = it.linkUserState.copy(
                    inputError = usernameResult.errorMessage,
                )
            ) }
        } else {
            _state.update { it.copy(
                urlLink = formatUrlWithReplacements(
                    url = state.value.linkUserState.href,
                    state.value.linkUserState.input.trim()
                ),
                feedsType = FeedsType.USER,
                linkUserState = it.linkUserState.copy(
                    inputError = null,
                )
            ) }
        }
    }

    private fun handleRepoDiscussionsClick() {
        val usernameResult = validateUsername.execute(state.value.repoDiscussionsState.input1)
        val repositoryResult = validateRepository.execute(state.value.repoDiscussionsState.input2)

        val hasError = listOf(
            usernameResult,
            repositoryResult,
        ).any { !it.successful }

        if (hasError) {
            _state.update {
                it.copy(
                    repoDiscussionsState = it.repoDiscussionsState.copy(
                        input1Error = usernameResult.errorMessage,
                        input2Error = repositoryResult.errorMessage,
                    )
                )
            }
        } else {
            _state.update {
                it.copy(
                    urlLink = formatUrlWithReplacements(
                        url = state.value.repoDiscussionsState.href,
                        state.value.repoDiscussionsState.input1.trim(),
                        state.value.repoDiscussionsState.input2.trim(),
                    ),
                    feedsType = FeedsType.REPO_DISCUSSIONS,
                    repoDiscussionsState = it.repoDiscussionsState.copy(
                        input1Error = null,
                        input2Error = null,
                    )
                )
            }
        }
    }

    private fun handleRepoDiscussionsCategoryClick() {
        val usernameResult = validateUsername.execute(state.value.repoDiscussionsCategoryState.input1)
        val repositoryResult = validateRepository.execute(state.value.repoDiscussionsCategoryState.input2)
        val categoryResult = validateCategory.execute(state.value.repoDiscussionsCategoryState.input3)

        val hasError = listOf(
            usernameResult,
            repositoryResult,
            categoryResult
        ).any { !it.successful }

        if (hasError) {
            _state.update {
                it.copy(
                    repoDiscussionsCategoryState = it.repoDiscussionsCategoryState.copy(
                        input1Error = usernameResult.errorMessage,
                        input2Error = repositoryResult.errorMessage,
                        input3Error = categoryResult.errorMessage,
                    )
                )
            }
        } else {
            _state.update {
                it.copy(
                    urlLink = formatUrlWithReplacements(
                        url = state.value.repoDiscussionsCategoryState.href,
                        state.value.repoDiscussionsCategoryState.input1.trim(),
                        state.value.repoDiscussionsCategoryState.input2.trim(),
                        state.value.repoDiscussionsCategoryState.input3.trim(),
                    ),
                    feedsType = FeedsType.REPO_DISCUSSIONS_CATEGORY,
                    repoDiscussionsCategoryState = it.repoDiscussionsCategoryState.copy(
                        input1Error = null,
                        input2Error = null,
                        input3Error = null,
                    )
                )
            }
        }
    }

    private fun handleSecurityAdvisories() {
        _state.update {
            it.copy(
                urlLink = state.value.securityAdvisoriesState.href,
                feedsType = FeedsType.SECURITY_ADVISORIES
            )
        }
    }

    private suspend fun getGithubFeeds() {
        githubRepository
            .getFeeds()
            .onSuccess { feeds ->
                val timelineState = TimelineState(
                    href = feeds.links?.timeLine?.href,
                    type = feeds.links?.timeLine?.type,
                    showSection = feeds.links?.timeLine?.href != null && feeds.links.timeLine.type?.validateContentType() != null
                )

                val linkUserState = LinkUserState(
                    href = feeds.links?.user?.href,
                    type = feeds.links?.user?.type,
                    showSection = feeds.links?.user?.href != null && feeds.links.user.type?.validateContentType() != null
                )

                val repoDiscussionsState = RepositoryDiscussionsState(
                    href = feeds.links?.repositoryDiscussions?.href,
                    type = feeds.links?.repositoryDiscussions?.type,
                    showSection = feeds.links?.repositoryDiscussions?.href != null && feeds.links.repositoryDiscussions.type?.validateContentType() != null
                )

                val repoDiscussionsCategoryState = RepositoryDiscussionsCategoryState(
                    href = feeds.links?.repositoryDiscussionsCategory?.href,
                    type = feeds.links?.repositoryDiscussionsCategory?.type,
                    showSection = feeds.links?.repositoryDiscussionsCategory?.href != null && feeds.links.repositoryDiscussionsCategory.type?.validateContentType() != null
                )

                val securityAdvisoriesState = SecurityAdvisoriesState(
                    href = feeds.links?.securityAdvisories?.href,
                    type = feeds.links?.securityAdvisories?.type,
                    showSection = feeds.links?.securityAdvisories?.href != null && feeds.links.securityAdvisories.type?.validateContentType() != null
                )

                _state.update { it.copy(
                    loading = false,
                    timelineState = timelineState,
                    linkUserState = linkUserState,
                    repoDiscussionsState = repoDiscussionsState,
                    repoDiscussionsCategoryState = repoDiscussionsCategoryState,
                    securityAdvisoriesState = securityAdvisoriesState,
                ) }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        loading = false,
                        errorMessage = error.toStringRes(),
                    )
                }
            }
    }

    fun clearUrlLink() {
        _state.update { it.copy(
            urlLink = null
        ) }
    }
}
