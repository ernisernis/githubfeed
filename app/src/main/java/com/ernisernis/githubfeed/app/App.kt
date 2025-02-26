package com.ernisernis.githubfeed.app

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
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
                    onClick = {
                        navController.navigate(Route.GithubDetail(DetailInput(param1 = "ABD")))
                    }
                )
            }

            composable<Route.GithubDetail>(
                typeMap = mapOf(
                    typeOf<DetailInput>() to GithubNavType.DetailInput
                )
            ) {
                val arguments = it.toRoute<Route.GithubDetail>()
                Log.d("ERNIS33", "App: ${arguments.detailInput.param1}")
                Log.d("ERNIS33", "App: ${arguments.detailInput.param2}")
                Log.d("ERNIS33", "App: ${arguments.detailInput.param3}")
                Log.d("ERNIS33", "App: ${arguments.detailInput.param4}")
            }
        }
    }
}