package com.ernisernis.githubfeed.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object GithubGraph: Route

    @Serializable
    data object GithubList: Route

    @Serializable
    data class GithubDetail(val detailParams: DetailParams): Route
}

@Serializable
data class DetailParams(
    val url: String,
)