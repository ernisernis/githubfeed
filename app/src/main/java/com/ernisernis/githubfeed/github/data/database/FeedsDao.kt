package com.ernisernis.githubfeed.github.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ernisernis.githubfeed.github.data.database.entities.FeedsEntity

@Dao
interface FeedsDao {
    @Upsert
    suspend fun upsertFeedsEntity(feeds: FeedsEntity)

    @Query("SELECT * FROM FeedsEntity LIMIT 1")
    suspend fun getFeedsEntity(): FeedsEntity?
}