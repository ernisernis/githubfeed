package com.ernisernis.githubfeed.github.data.repository

import com.ernisernis.githubfeed.core.domain.util.DataError
import com.ernisernis.githubfeed.core.domain.util.Result
import com.ernisernis.githubfeed.core.domain.util.map
import com.ernisernis.githubfeed.core.domain.util.onError
import com.ernisernis.githubfeed.core.domain.util.onSuccess
import com.ernisernis.githubfeed.github.data.database.FeedsDao
import com.ernisernis.githubfeed.github.data.mappers.toFeeds
import com.ernisernis.githubfeed.github.data.mappers.toFeedsEntity
import com.ernisernis.githubfeed.github.data.network.RemoteGithubDataSource
import com.ernisernis.githubfeed.github.domain.Feeds
import com.ernisernis.githubfeed.github.domain.GithubRepository
import com.prof18.rssparser.model.RssChannel

class DefaultGithubRepository(
    private val remoteGithubDataSource: RemoteGithubDataSource,
    private val feedsDao: FeedsDao,
): GithubRepository {
    override suspend fun getFeeds(): Result<Feeds, DataError.Remote> {
        return remoteGithubDataSource
            .getFeeds()
            .map { dto ->
                dto.toFeeds()
            }
            .onSuccess { feeds ->
                // Save result to local db
                feedsDao.upsertFeedsEntity(feeds.toFeedsEntity())
                Result.Success(feeds)
            }
            .onError { error ->
                // Check if feeds is available in local db
                val localResult = feedsDao.getFeedsEntity()

                return if (localResult != null) {
                    // Return cached result
                    Result.Success(localResult.toFeeds())
                } else {
                    // Forward error
                    Result.Error(error)
                }
            }
    }

    override suspend fun getFeedsDetail(url: String): Result<RssChannel, DataError.Remote> {
        return remoteGithubDataSource
            .getFeedsDetail(url)
            .map { rssChannel ->
                rssChannel
            }
    }
}
