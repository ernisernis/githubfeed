package com.ernisernis.githubfeed.github.domain

import com.ernisernis.githubfeed.core.domain.util.DataError
import com.ernisernis.githubfeed.core.domain.util.Result

interface GithubRepository {
    suspend fun getFeeds(): Result<Feeds, DataError.Remote>
}