package com.ernisernis.githubfeed.github.presentation.github_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ernisernis.githubfeed.app.DetailParams
import com.ernisernis.githubfeed.core.domain.util.onError
import com.ernisernis.githubfeed.core.domain.util.onSuccess
import com.ernisernis.githubfeed.github.domain.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GithubDetailViewModel @Inject constructor(
    private val githubRepository: GithubRepository,
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
        viewModelScope.launch {
            githubRepository.getFeedsDetail(detailParams.url)
                .onSuccess { rssChannel ->
                    _state.update {
                        it.copy(
                            rssChannel = rssChannel,
                            loading = false,
                        )
                    }
                }
                .onError {
                    // TODO: Handle error
                    _state.update {
                        it.copy(
                            loading = false,
                        )
                    }
                }
        }

    }
}