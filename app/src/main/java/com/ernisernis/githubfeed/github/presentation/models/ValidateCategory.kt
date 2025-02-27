package com.ernisernis.githubfeed.github.presentation.models

import com.ernisernis.githubfeed.R
import com.ernisernis.githubfeed.github.domain.ValidationResult

class ValidateCategory {

    fun execute(category: String): ValidationResult {
        if (category.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.error_category_empty,
            )
        }

        if (category.length > 50) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.error_category_long,
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}