package com.ernisernis.githubfeed.core.presentation.util


fun String.validateContentType(): String? {
   return if (this == "application/atom+xml") {
       this
   } else {
       null
   }
}