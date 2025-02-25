package com.ernisernis.githubfeed.github.presentation.github_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ernisernis.githubfeed.core.domain.util.onSuccess
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
            .onSuccess { error ->
                Log.d("ERNIS33", ": $error")
            }
    }
}
