package com.ernisernis.githubfeed.app

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object GithubNavType {

    val DetailInput = object : NavType<DetailInput>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): DetailInput? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): DetailInput {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: DetailInput): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: DetailInput) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}