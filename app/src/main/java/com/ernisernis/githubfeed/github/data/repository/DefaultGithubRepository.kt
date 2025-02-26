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
                feedsDao.upsertFeedsEntity(feeds.toFeedsEntity())
                Result.Success(feeds)
            }
            .onError { error ->
                // TODO: Check if feedsDao entity exists and return, if not, return error given
//                val localResult = feedsDao.getFeedsEntity()
                Result.Error(error)
            }
    }
}
