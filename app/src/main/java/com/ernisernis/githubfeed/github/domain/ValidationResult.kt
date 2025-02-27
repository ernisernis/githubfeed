package com.ernisernis.githubfeed.github.domain

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable

@Stable
data class ValidationResult(
    val successful: Boolean,
    @StringRes val errorMessage: Int? = null,
)