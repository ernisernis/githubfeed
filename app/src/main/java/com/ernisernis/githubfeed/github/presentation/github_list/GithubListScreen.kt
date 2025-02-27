package com.ernisernis.githubfeed.github.presentation.github_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ernisernis.githubfeed.R
import com.ernisernis.githubfeed.app.DetailParams
import com.ernisernis.githubfeed.core.presentation.util.Dimens
import com.ernisernis.githubfeed.github.domain.FeedsType
import com.ernisernis.githubfeed.github.presentation.components.GithubLoadingIndicator
import com.ernisernis.githubfeed.github.presentation.github_list.components.FeedInputField
import com.ernisernis.githubfeed.github.presentation.github_list.components.FeedItem
import com.ernisernis.githubfeed.ui.theme.GithubFeedTheme


@Composable
fun GithubListScreenRoot(
    viewModel: GithubListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onClick: (DetailParams) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.loading) {
        GithubLoadingIndicator(
            modifier = modifier,
            color = MaterialTheme.colorScheme.onBackground,
        )
    } else {
        GithubListScreen(
            state = state,
            modifier = modifier,
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
                onClick(DetailParams(url = it, feedsType = state.feedsType))
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
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(Dimens.GithubListContainerPadding),
        verticalArrangement = Arrangement.spacedBy(Dimens.GithubListContainerItemSpacing)
    ) {
        // Timeline
        if (state.timelineState.showSection) {
            FeedItem(
                title = stringResource(state.timelineState.title),
                urlPathLink = state.timelineState.href,
                onClick = {
                    onAction(GithubListAction.OnFeedsClick(FeedsType.TIMELINE))
                },
            )
        }

        // User
        if (state.linkUserState.showSection) {
            FeedItem(
                title = stringResource(state.linkUserState.title),
                urlPathLink = state.linkUserState.href,
                onClick = {
                    onAction(GithubListAction.OnFeedsClick(FeedsType.USER))
                },
                inputContent = {
                    FeedInputField(
                        inputText = state.linkUserState.input,
                        labelText = stringResource(state.linkUserState.inputLabel),
                        onTextChange = {
                            onAction(GithubListAction.OnLinkUserInputChange(it))
                        }
                    )
                }
            )
        }

        // Discussions
        if (state.repoDiscussionsState.showSection) {
            FeedItem(
                title = stringResource(state.repoDiscussionsState.title),
                urlPathLink = state.repoDiscussionsState.href,
                onClick = {
                    onAction(GithubListAction.OnFeedsClick(FeedsType.REPO_DISCUSSIONS))
                },
                inputContent = {
                    FeedInputField(
                        inputText = state.repoDiscussionsState.input1,
                        labelText = stringResource(state.repoDiscussionsState.input1Label),
                        onTextChange = {
                            onAction(GithubListAction.OnRepoDiscussionsInput1Change(it))
                        }
                    )
                    FeedInputField(
                        inputText = state.repoDiscussionsState.input2,
                        labelText = stringResource(state.repoDiscussionsState.input2Label),
                        onTextChange = {
                            onAction(GithubListAction.OnRepoDiscussionsInput2Change(it))
                        }
                    )
                }
            )
        }

        // Discussions Category
        if (state.repoDiscussionsCategoryState.showSection) {
            FeedItem(
                title = stringResource(state.repoDiscussionsCategoryState.title),
                urlPathLink = state.repoDiscussionsCategoryState.href,
                onClick = {
                    onAction(GithubListAction.OnFeedsClick(FeedsType.REPO_DISCUSSIONS_CATEGORY))
                },
                inputContent = {
                    FeedInputField(
                        inputText = state.repoDiscussionsCategoryState.input1,
                        labelText = stringResource(state.repoDiscussionsCategoryState.input1Label),
                        onTextChange = {
                            onAction(GithubListAction.OnRepoDiscussionsCategoryInput1Change(it))
                        }
                    )
                    FeedInputField(
                        inputText = state.repoDiscussionsCategoryState.input2,
                        labelText = stringResource(state.repoDiscussionsCategoryState.input2Label),
                        onTextChange = {
                            onAction(GithubListAction.OnRepoDiscussionsCategoryInput2Change(it))
                        }
                    )
                    FeedInputField(
                        inputText = state.repoDiscussionsCategoryState.input3,
                        labelText = stringResource(state.repoDiscussionsCategoryState.input3Label),
                        onTextChange = {
                            onAction(GithubListAction.OnRepoDiscussionsCategoryInput3Change(it))
                        }
                    )
                }
            )
        }

        // Security Advisories
        if (state.securityAdvisoriesState.showSection) {
            FeedItem(
                title = stringResource(state.securityAdvisoriesState.title),
                urlPathLink = state.securityAdvisoriesState.href,
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
    GithubFeedTheme {
        GithubListScreen(
            state = GithubListState(),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            onAction = {}
        )
    }
}