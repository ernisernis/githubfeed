package com.ernisernis.githubfeed.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object GithubGraph: Route

    @Serializable
    data object GithubList: Route

    @Serializable
    data class GithubDetail(val detailInput: DetailInput): Route
}

@Serializable
data class DetailInput(
    val param1: String? = null,
    val param2: String? = null,
    val param3: String? = null,
    val param4: String? = null,
)