package com.ernisernis.githubfeed.github.presentation.github_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class GithubListViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(GithubListState())
    val state = _state
        .onStart {
            // Init if needed
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
