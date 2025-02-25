package com.ernisernis.githubfeed.github.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ernisernis.githubfeed.github.data.database.converters.GithubTypeConverter
import com.ernisernis.githubfeed.github.data.database.entities.FeedsEntity


@Database(
    entities = [
        FeedsEntity::class,
    ],
    version = 1
)
@TypeConverters(
    GithubTypeConverter::class
)
abstract class GithubDatabase: RoomDatabase() {
    abstract val feedsDao: FeedsDao

    companion object {
        const val DB_NAME = "github.db"
    }
}