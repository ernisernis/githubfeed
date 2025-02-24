package com.ernisernis.githubfeed.app

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.ernisernis.githubfeed.github.presentation.github_list.GithubListScreenRoot
import com.ernisernis.githubfeed.github.presentation.github_list.GithubListViewModel


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
                    viewModel = viewModel
                )
            }
        }
    }
}