package com.ernisernis.githubfeed.core.presentation.util

import android.os.Build
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale


fun String.validateContentType(): String? {
   return if (this == "application/atom+xml") {
       this
   } else {
       null
   }
}

fun String.getFormattedDateTime(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        try {
            val instant = Instant.parse(this)
            val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy • hh:mm a", Locale.getDefault())
            val zonedDateTime = instant.atZone(ZoneId.systemDefault())
            zonedDateTime.format(formatter)
        } catch (e: Exception) {
            this
        }
    } else {
        this
    }
}