package com.ernisernis.githubfeed.github.data.network

import com.ernisernis.githubfeed.core.domain.util.DataError
import com.ernisernis.githubfeed.core.domain.util.Result
import com.ernisernis.githubfeed.github.data.dto.FeedsDto
import com.prof18.rssparser.model.RssChannel

interface RemoteGithubDataSource {
    suspend fun getFeeds(): Result<FeedsDto, DataError.Remote>
    suspend fun getFeedsDetail(url: String): Result<RssChannel, DataError.Remote>
}