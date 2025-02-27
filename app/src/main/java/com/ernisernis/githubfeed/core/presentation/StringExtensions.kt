package com.ernisernis.githubfeed.core.presentation


fun String.validateContentType(): String? {
   return if (this == "application/atom+xml") {
       this
   } else {
       null
   }
}