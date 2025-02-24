package com.ernisernis.githubfeed.github.presentation.github_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ernisernis.githubfeed.core.domain.util.onSuccess
import com.ernisernis.githubfeed.github.domain.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class GithubListViewModel @Inject constructor(
    private val githubRepository: GithubRepository,
) : ViewModel() {
    // TODO: Add OneTimeWhileSubscribed helper function to emit only once after active subscription in order to reduce API calls
    private val _state = MutableStateFlow(GithubListState())
    val state = _state
        .onStart {
            githubRepository
                .getFeeds()
                .onSuccess { feeds ->
                    Log.d("ERNIS33", ": $feeds")
                }
                .onSuccess { error ->
                    Log.d("ERNIS33", ": $error")
                }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = GithubListState()
        )


    fun onAction(action: GithubListAction) {
        when (action) {
            else -> Unit
        }
    }
}
