package com.ernisernis.githubfeed.github.data.database.converters

import androidx.room.TypeConverter
import com.ernisernis.githubfeed.github.data.dto.LinksDto
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object GithubTypeConverter {
    @TypeConverter
    fun linksDtoFromString(value: String): LinksDto {
        return Json.decodeFromString<LinksDto>(value)
    }

    @TypeConverter
    fun linksDtoFromObject(value: LinksDto): String {
        return Json.encodeToString(value)
    }
}