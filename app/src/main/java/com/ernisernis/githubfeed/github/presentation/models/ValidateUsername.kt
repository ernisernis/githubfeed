package com.ernisernis.githubfeed.github.presentation.models

import com.ernisernis.githubfeed.R
import com.ernisernis.githubfeed.github.domain.ValidationResult

class ValidateUsername {

    fun execute(username: String): ValidationResult {
        if (username.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.error_username_empty,
            )
        }

        if (username.length > 50) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.error_username_long,
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}