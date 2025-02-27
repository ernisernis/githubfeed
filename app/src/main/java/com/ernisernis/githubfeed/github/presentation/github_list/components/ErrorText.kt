package com.ernisernis.githubfeed.github.presentation.github_list.components

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun ErrorText(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
) {
    Text(
        modifier = modifier,
        text = stringResource(text),
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall,
    )
}