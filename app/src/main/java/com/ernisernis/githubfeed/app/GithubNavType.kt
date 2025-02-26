package com.ernisernis.githubfeed.app

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object GithubNavType {

    val DetailParams = object : NavType<DetailParams>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): DetailParams? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): DetailParams {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: DetailParams): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: DetailParams) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}