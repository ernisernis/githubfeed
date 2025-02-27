package com.ernisernis.githubfeed.github.data.network

import com.ernisernis.githubfeed.core.data.networking.constructUrl
import com.ernisernis.githubfeed.core.data.networking.safeCall
import com.ernisernis.githubfeed.core.domain.util.DataError
import com.ernisernis.githubfeed.core.domain.util.Result
import com.ernisernis.githubfeed.core.domain.util.map
import com.ernisernis.githubfeed.github.data.dto.FeedsDto
import com.prof18.rssparser.RssParser
import com.prof18.rssparser.exception.RssParsingException
import com.prof18.rssparser.model.RssChannel
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType.Application.Atom
import io.ktor.http.contentType

class KtorRemoteGithubDataSource(
    private val httpClient: HttpClient,
    private val rssParser: RssParser,
) : RemoteGithubDataSource {
    override suspend fun getFeeds(): Result<FeedsDto, DataError.Remote> {
        return safeCall<FeedsDto> {
            httpClient.get(
                urlString = constructUrl("/feeds"),
            )
        }.map { response ->
            response
        }
    }

    override suspend fun getFeedsDetailFromRawUrl(url: String): Result<RssChannel, DataError.Remote> {
        return try {
            val rssChannel: RssChannel = rssParser.getRssChannel(url)
            return Result.Success(rssChannel)
        } catch (e: RssParsingException) {
           Result.Error(DataError.Remote.RSS_PARSING)
        } catch (e: Exception) {
            Result.Error(DataError.Remote.UNKNOWN)
        }
    }

    override suspend fun getFeedsDetail(url: String): Result<RssChannel, DataError.Remote> {
        return safeCall<String> {
            httpClient.get(
                urlString = url
            ) {
                contentType(Atom)
            }
        }.map { atomString ->
            return try {
                val rssChannel: RssChannel = rssParser.parse(atomString)
                return Result.Success(rssChannel)
            } catch (e: RssParsingException) {
                Result.Error(DataError.Remote.RSS_PARSING)
            }
        }
    }
}