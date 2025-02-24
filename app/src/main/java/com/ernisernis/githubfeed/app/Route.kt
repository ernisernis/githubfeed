package com.ernisernis.githubfeed.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object GithubGraph: Route

    @Serializable
    data object GithubList: Route
}