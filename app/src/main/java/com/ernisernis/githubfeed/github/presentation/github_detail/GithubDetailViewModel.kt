package com.ernisernis.githubfeed.github.presentation.github_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ernisernis.githubfeed.app.DetailParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class GithubDetailViewModel @Inject constructor(

) : ViewModel() {
   private val _state = MutableStateFlow(GithubDetailState())
    val state = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = GithubDetailState()
        )

    fun onAction(action: GithubDetailAction) {
        when (action) {
            GithubDetailAction.TestAction -> TODO()
        }
    }

    fun initData(detailParams: DetailParams) {
        _state.update { it.copy(
            urlLink = detailParams.url
        ) }
    }
}