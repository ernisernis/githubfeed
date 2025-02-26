package com.ernisernis.githubfeed.github.presentation.github_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ernisernis.githubfeed.app.DetailParams
import com.ernisernis.githubfeed.github.domain.FeedsType
import com.ernisernis.githubfeed.github.presentation.github_list.components.FeedInputField
import com.ernisernis.githubfeed.github.presentation.github_list.components.FeedItem
import com.ernisernis.githubfeed.github.presentation.github_list.components.GithubListLoading
import com.ernisernis.githubfeed.ui.theme.GithubFeedTheme


@Composable
fun GithubListScreenRoot(
    viewModel: GithubListViewModel = hiltViewModel(),
    onClick: (DetailParams) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.loading) {
        GithubListLoading(
            modifier = Modifier
                .fillMaxSize()
        )
    } else {
        GithubListScreen(
            state = state,
            modifier = Modifier
                .statusBarsPadding()
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            onAction = { action ->
                when (action)  {
                    else -> Unit
                }
                viewModel.onAction(action)
            }
        )
    }

    LaunchedEffect(key1 = state.urlLink != null) {
        if (state.urlLink != null) {
            state.urlLink?.let {
                onClick(DetailParams(url = it))
                viewModel.clearUrlLink()
            }
        }
    }

}

@Composable
fun GithubListScreen(
    state: GithubListState,
    modifier: Modifier = Modifier,
    onAction: (GithubListAction) -> Unit,
) {
    AnimatedVisibility(
        visible = state.feedsUi != null
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Timeline
            FeedItem(
                title = state.timelineState.title,
                urlPathLink = state.feedsUi?.timelineUrl ?: "",
                onClick = {
                    onAction(GithubListAction.OnFeedsClick(FeedsType.TIMELINE))
                },
            )

            // User
            FeedItem(
                title = state.linkUserState.title,
                urlPathLink = state.feedsUi?.userUrl ?: "",
                onClick = {
                    onAction(GithubListAction.OnFeedsClick(FeedsType.USER))
                },
                inputContent = {
                    FeedInputField(
                        inputText = state.linkUserState.input,
                        labelText = state.linkUserState.inputLabel,
                        onTextChange = {
                            onAction(GithubListAction.OnLinkUserInputChange(it))
                        }
                    )
                }
            )

            // Discussions
            FeedItem(
                title = state.repoDiscussionsState.title,
                urlPathLink = state.feedsUi?.repoDiscussionsUrl ?: "",
                onClick = {
                    onAction(GithubListAction.OnFeedsClick(FeedsType.REPO_DISCUSSIONS))
                },
                inputContent = {
                    FeedInputField(
                        inputText = state.repoDiscussionsState.input1,
                        labelText = state.repoDiscussionsState.input1Label,
                        onTextChange = {
                            onAction(GithubListAction.OnRepoDiscussionsInput1Change(it))
                        }
                    )
                    FeedInputField(
                        inputText = state.repoDiscussionsState.input2,
                        labelText = state.repoDiscussionsState.input2Label,
                        onTextChange = {
                            onAction(GithubListAction.OnRepoDiscussionsInput2Change(it))
                        }
                    )
                }
            )

            // Discussions Category
            FeedItem(
                title = state.repoDiscussionsCategoryState.title,
                urlPathLink = state.feedsUi?.repoDiscussionsCategoryUrl ?: "",
                onClick = {
                    onAction(GithubListAction.OnFeedsClick(FeedsType.REPO_DISCUSSIONS_CATEGORY))
                },
                inputContent = {
                    FeedInputField(
                        inputText = state.repoDiscussionsCategoryState.input1,
                        labelText = state.repoDiscussionsCategoryState.input1Label,
                        onTextChange = {
                            onAction(GithubListAction.OnRepoDiscussionsCategoryInput1Change(it))
                        }
                    )
                    FeedInputField(
                        inputText = state.repoDiscussionsCategoryState.input2,
                        labelText = state.repoDiscussionsCategoryState.input2Label,
                        onTextChange = {
                            onAction(GithubListAction.OnRepoDiscussionsCategoryInput2Change(it))
                        }
                    )
                    FeedInputField(
                        inputText = state.repoDiscussionsCategoryState.input3,
                        labelText = state.repoDiscussionsCategoryState.input3Label,
                        onTextChange = {
                            onAction(GithubListAction.OnRepoDiscussionsCategoryInput3Change(it))
                        }
                    )
                }
            )

            // Security Advisories
            FeedItem(
                title = state.securityAdvisoriesState.title,
                urlPathLink = state.feedsUi?.securityAdvisoriesUrl ?: "",
                onClick = {
                    onAction(GithubListAction.OnFeedsClick(FeedsType.SECURITY_ADVISORIES))
                },
            )
        }
    }
}

@Preview
@Composable
fun GithubListScreenPreview() {
    val state = GithubListState()
    GithubFeedTheme {
        GithubListScreen(
            state = state,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            onAction = {}
        )
    }
}