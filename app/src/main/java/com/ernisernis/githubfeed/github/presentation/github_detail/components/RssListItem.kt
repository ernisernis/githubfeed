package com.ernisernis.githubfeed.github.presentation.github_detail.components

import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.viewinterop.AndroidView
import coil3.compose.AsyncImage
import com.ernisernis.githubfeed.core.presentation.util.Dimens
import com.prof18.rssparser.model.RssItem

@Composable
fun RssListItem(
    modifier: Modifier = Modifier,
    item: RssItem
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4))
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(Dimens.GithubDetailItemPadding),
        verticalArrangement = Arrangement.spacedBy(Dimens.GithubDetailRssItemSpacing),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.GithubDetailRssItemSpacing)
        ) {
            item.author?.let {
                Text(
                    text = "By: $it",
                    color = MaterialTheme.colorScheme.onTertiary,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
            item.pubDate?.let {
                Text(
                    text = "At: $it",
                    color = MaterialTheme.colorScheme.onTertiary,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        item.title?.let {
           Text(
               text = it,
               color = MaterialTheme.colorScheme.onTertiary,
               style = MaterialTheme.typography.bodyMedium,
           )
        }
        item.link?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.onTertiary,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        item.description?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.onTertiary,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        item.image?.let {
           AsyncImage(
               model = it,
               contentDescription = null
           )
        }
        item.commentsUrl?.let {
            Text(
                text = "Comments url: $it",
                color = MaterialTheme.colorScheme.onTertiary,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        item.content?.let {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        loadData(it, "text/html", "UTF-8")
                    }
                }
            )
        }
    }
}