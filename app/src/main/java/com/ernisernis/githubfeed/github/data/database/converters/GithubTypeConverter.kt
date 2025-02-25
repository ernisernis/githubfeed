package com.ernisernis.githubfeed.github.data.database.converters

import androidx.room.TypeConverter
import com.ernisernis.githubfeed.github.data.database.entities.LinksEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object GithubTypeConverter {
    @TypeConverter
    fun fromLinksEntity(links: LinksEntity): String {
        return Json.encodeToString(links)
    }

    @TypeConverter
    fun toLinksEntity(json: String): LinksEntity {
        return Json.decodeFromString(json)
    }
}