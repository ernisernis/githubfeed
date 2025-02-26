package com.ernisernis.githubfeed.di

import com.ernisernis.githubfeed.core.data.networking.HttpClientFactory
import com.ernisernis.githubfeed.github.data.network.KtorRemoteGithubDataSource
import com.ernisernis.githubfeed.github.data.network.RemoteGithubDataSource
import com.ernisernis.githubfeed.github.data.repository.DefaultGithubRepository
import com.ernisernis.githubfeed.github.domain.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import android.content.Context
import androidx.room.Room
import com.ernisernis.githubfeed.github.data.database.FeedsDao
import com.ernisernis.githubfeed.github.data.database.GithubDatabase
import com.prof18.rssparser.RssParser
import com.prof18.rssparser.RssParserBuilder
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClientEngine(): HttpClientEngine {
        return OkHttp.create()
    }

    @Provides
    @Singleton
    fun provideHttpClientFactory(engine: HttpClientEngine): HttpClient {
        return HttpClientFactory.create(engine)
    }

    @Provides
    @Singleton
    fun provideKtorRemoteGithubDataSource(httpClient: HttpClient, rssParser: RssParser): RemoteGithubDataSource {
        return KtorRemoteGithubDataSource(httpClient, rssParser)
    }

    @Provides
    @Singleton
    fun provideDefaultGithubRepository(remoteGithubDataSource: RemoteGithubDataSource, feedsDao: FeedsDao): GithubRepository {
        return DefaultGithubRepository(remoteGithubDataSource, feedsDao)
    }

    @Provides
    @Singleton
    fun provideFeedsDao(database: GithubDatabase): FeedsDao {
        return database.feedsDao
    }

    @Provides
    @Singleton
    fun provideGithubDatabase(
        @ApplicationContext context: Context
    ): GithubDatabase {
        return Room.databaseBuilder(
            context,
            GithubDatabase::class.java,
            GithubDatabase.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRssParser(): RssParser {
        val builder = RssParserBuilder(
            callFactory = OkHttpClient(),
            charset = Charsets.UTF_8,
        )
        return builder.build()
    }
}