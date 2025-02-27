package com.ernisernis.githubfeed.github.presentation.github_list.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ernisernis.githubfeed.ui.theme.GithubFeedTheme


@Composable
fun FeedInputField(
    modifier: Modifier = Modifier,
    inputText: String,
    labelText: String,
    @StringRes errorMessage: Int? = null,
    onTextChange: (String) -> Unit,
    onDoneAction: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
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
            ),
            isError = errorMessage != null,
        )
        if (errorMessage != null) {
            ErrorText(
                text = errorMessage,
                modifier = Modifier
                    .padding(top = 3.dp)
                    .align(Alignment.End)
            )
        }
    }
}

@Preview
@Composable
fun FeedInputFieldPreview() {
    GithubFeedTheme {
        FeedInputField(
            labelText = "Test Label",
            inputText = "",
            onTextChange = {},
            errorMessage = null,
        )
    }
}