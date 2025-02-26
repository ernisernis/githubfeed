package com.ernisernis.githubfeed.github.presentation.github_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ernisernis.githubfeed.core.domain.util.onError
import com.ernisernis.githubfeed.core.domain.util.onSuccess
import com.ernisernis.githubfeed.core.presentation.formatUrlWithReplacements
import com.ernisernis.githubfeed.core.presentation.formatUserUrlLink
import com.ernisernis.githubfeed.github.domain.FeedsType
import com.ernisernis.githubfeed.github.domain.GithubRepository
import com.ernisernis.githubfeed.github.presentation.models.toFeedsUi
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
                            urlLink = state.value.feedsUi?.linksUi?.timeLine?.href,
                        ) }
                    }
                    FeedsType.USER -> {
                        _state.update { it.copy(
                            urlLink = formatUrlWithReplacements(
                                url = state.value.feedsUi?.linksUi?.user?.href,
                                state.value.linkUserState.input
                            )
                        ) }
                    }
                    FeedsType.REPO_DISCUSSIONS -> {
                        _state.update {
                            it.copy(
                                urlLink = formatUrlWithReplacements(
                                    url = state.value.feedsUi?.linksUi?.repositoryDiscussions?.href,
                                    state.value.repoDiscussionsState.input1,
                                    state.value.repoDiscussionsState.input2,
                                )
                            )
                        }

                    }
                    FeedsType.REPO_DISCUSSIONS_CATEGORY -> {
                        _state.update {
                            it.copy(
                                urlLink = formatUrlWithReplacements(
                                    url = state.value.feedsUi?.linksUi?.repositoryDiscussionsCategory?.href,
                                    state.value.repoDiscussionsCategoryState.input1,
                                    state.value.repoDiscussionsCategoryState.input2,
                                    state.value.repoDiscussionsCategoryState.input3,
                                )
                            )
                        }
                    }
                    FeedsType.SECURITY_ADVISORIES -> {
                        _state.update {
                            it.copy(
                                urlLink = state.value.feedsUi?.linksUi?.securityAdvisories?.href
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
                _state.update { it.copy(
                    loading = false,
                    feedsUi = feeds.toFeedsUi()
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
