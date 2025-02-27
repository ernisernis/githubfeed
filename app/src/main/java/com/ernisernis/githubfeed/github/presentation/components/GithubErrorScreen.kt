package com.ernisernis.githubfeed.github.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun GithubErrorScreen(
    modifier: Modifier = Modifier,
    @StringRes errorMessage: Int?,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        errorMessage?.let {
            Text(
                text = stringResource(it),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
            )
        }
    }

}