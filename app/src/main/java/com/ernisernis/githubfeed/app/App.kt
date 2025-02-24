package com.ernisernis.githubfeed.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController


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
                Box(
                    modifier = Modifier
                        .background(Color.Red)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Hello from Github list screen!"
                    )
                }
            }
        }
    }
}