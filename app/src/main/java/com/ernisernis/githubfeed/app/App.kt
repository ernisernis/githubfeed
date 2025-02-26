package com.ernisernis.githubfeed.app

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ernisernis.githubfeed.github.presentation.github_detail.GithubDetailScreenRoot
import com.ernisernis.githubfeed.github.presentation.github_detail.GithubDetailViewModel
import com.ernisernis.githubfeed.github.presentation.github_list.GithubListScreenRoot
import com.ernisernis.githubfeed.github.presentation.github_list.GithubListViewModel
import kotlin.reflect.typeOf


@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background),
        startDestination = Route.GithubGraph
    ) {
        navigation<Route.GithubGraph>(
            startDestination = Route.GithubList
        ) {
            composable<Route.GithubList> {
                val viewModel = hiltViewModel<GithubListViewModel>()

                GithubListScreenRoot(
                    viewModel = viewModel,
                    onClick = { detailParams ->
                        navController.navigate(Route.GithubDetail(detailParams))
                    }
                )
            }

            composable<Route.GithubDetail>(
                typeMap = mapOf(
                    typeOf<DetailParams>() to GithubNavType.DetailParams
                )
            ) {
                val arguments = it.toRoute<Route.GithubDetail>()
                val viewModel = hiltViewModel<GithubDetailViewModel>()

                LaunchedEffect(arguments.detailParams) {
                    viewModel.initData(arguments.detailParams)
                }

                GithubDetailScreenRoot(
                    viewModel = viewModel,
                )
            }
        }
    }
}