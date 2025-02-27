package com.ernisernis.githubfeed.github.presentation.github_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.ernisernis.githubfeed.ui.theme.GithubFeedTheme


@Composable
fun FeedInputField(
    modifier: Modifier = Modifier,
    inputText: String,
    labelText: String,
    onTextChange: (String) -> Unit,
    onDoneAction: () -> Unit = {},
) {
    OutlinedTextField(
        value = inputText,
        onValueChange = {
            onTextChange(it)
        },
        modifier = modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedTextColor = MaterialTheme.colorScheme.onSecondary,
            focusedContainerColor = MaterialTheme.colorScheme.secondary,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
            focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f),
            cursorColor = MaterialTheme.colorScheme.onSecondary,
        ),
        label = {
            Text(
                text = labelText,
                color = MaterialTheme.colorScheme.onSecondary,
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDoneAction()
            }
        )
    )
}

@Preview
@Composable
fun FeedInputFieldPreview() {
    GithubFeedTheme {
        FeedInputField(
            labelText = "Test Label",
            inputText = "",
            onTextChange = {}
        )
    }
}