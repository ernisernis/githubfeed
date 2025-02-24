package com.ernisernis.githubfeed.di

import com.ernisernis.githubfeed.core.data.networking.HttpClientFactory
import com.ernisernis.githubfeed.github.data.network.KtorRemoteGithubDataSource
import com.ernisernis.githubfeed.github.data.network.RemoteGithubDataSource
import com.ernisernis.githubfeed.github.data.repository.DefaultGithubRepository
import com.ernisernis.githubfeed.github.domain.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
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
    fun provideKtorRemoteGithubDataSource(httpClient: HttpClient): RemoteGithubDataSource {
        return KtorRemoteGithubDataSource(httpClient)
    }

    @Provides
    @Singleton
    fun provideDefaultGithubRepository(remoteGithubDataSource: RemoteGithubDataSource): GithubRepository {
        return DefaultGithubRepository(remoteGithubDataSource)
    }
}