package com.ernisernis.githubfeed.github.presentation.models

import com.ernisernis.githubfeed.R
import com.ernisernis.githubfeed.github.domain.ValidationResult

class ValidateRepository {

    fun execute(repo: String): ValidationResult {
        if (repo.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.error_repo_empty,
            )
        }

        if (repo.length > 50) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.error_repo_long,
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}
