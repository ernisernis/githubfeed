package com.ernisernis.githubfeed.github.presentation.github_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ernisernis.githubfeed.core.domain.util.onError
import com.ernisernis.githubfeed.core.domain.util.onSuccess
import com.ernisernis.githubfeed.core.presentation.util.formatUrlWithReplacements
import com.ernisernis.githubfeed.core.presentation.util.validateContentType
import com.ernisernis.githubfeed.github.domain.FeedsType
import com.ernisernis.githubfeed.github.domain.GithubRepository
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
                // TODO: Add validations before forwarding the user

                when (action.type) {
                    FeedsType.TIMELINE -> {
                        _state.update { it.copy(
                            urlLink = state.value.timelineState.href,
                            feedsType = FeedsType.TIMELINE,
                        ) }
                    }
                    FeedsType.USER -> {
                        _state.update { it.copy(
                            urlLink = formatUrlWithReplacements(
                                url = state.value.linkUserState.href,
                                state.value.linkUserState.input
                            ),
                            feedsType = FeedsType.USER,
                        ) }
                    }
                    FeedsType.REPO_DISCUSSIONS -> {
                        _state.update {
                            it.copy(
                                urlLink = formatUrlWithReplacements(
                                    url = state.value.repoDiscussionsState.href,
                                    state.value.repoDiscussionsState.input1,
                                    state.value.repoDiscussionsState.input2,
                                ),
                                feedsType = FeedsType.REPO_DISCUSSIONS,
                            )
                        }
                    }
                    FeedsType.REPO_DISCUSSIONS_CATEGORY -> {
                        _state.update {
                            it.copy(
                                urlLink = formatUrlWithReplacements(
                                    url = state.value.repoDiscussionsCategoryState.href,
                                    state.value.repoDiscussionsCategoryState.input1,
                                    state.value.repoDiscussionsCategoryState.input2,
                                    state.value.repoDiscussionsCategoryState.input3,
                                ),
                                feedsType = FeedsType.REPO_DISCUSSIONS_CATEGORY,
                            )
                        }
                    }
                    FeedsType.SECURITY_ADVISORIES -> {
                        _state.update {
                            it.copy(
                                urlLink = state.value.securityAdvisoriesState.href,
                                feedsType = FeedsType.SECURITY_ADVISORIES
                            )
                        }
                    }
                }
            }
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
                // TODO: Handle error
            }
    }

    fun clearUrlLink() {
        _state.update { it.copy(
            urlLink = null
        ) }
    }
}
