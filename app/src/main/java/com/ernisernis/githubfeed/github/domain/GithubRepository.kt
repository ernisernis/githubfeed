package com.ernisernis.githubfeed.github.domain

import com.ernisernis.githubfeed.core.domain.util.DataError
import com.ernisernis.githubfeed.core.domain.util.Result
import com.prof18.rssparser.model.RssChannel

interface GithubRepository {
    suspend fun getFeeds(): Result<Feeds, DataError.Remote>
    suspend fun getFeedsDetail(url: String): Result<RssChannel, DataError.Remote>
}