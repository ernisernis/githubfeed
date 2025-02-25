package com.ernisernis.githubfeed.github.data.database

import androidx.room.Dao
import androidx.room.Upsert
import com.ernisernis.githubfeed.github.domain.Feeds

@Dao
interface FeedsDao {
    @Upsert
    suspend fun upsertFeedsEntity(feeds: Feeds)
}