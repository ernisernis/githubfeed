package com.ernisernis.githubfeed.github.presentation.models

import com.ernisernis.githubfeed.github.domain.Feeds
import com.ernisernis.githubfeed.github.domain.Link
import com.ernisernis.githubfeed.github.domain.Links

data class FeedsUi(
    val timelineUrl: String,
    val userUrl: String,
    val repositoryDiscussionsUrl: String,
    val repositoryDiscussionsCategoryUrl: String,
    val securityAdvisoriesUrl: String,
    val linksUi: LinksUi,
)

data class LinksUi(
    val timeLine: LinkUi,
    val user: LinkUi,
    val repositoryDiscussions: LinkUi,
    val repositoryDiscussionsCategory: LinkUi,
    val securityAdvisories: LinkUi,
)

data class LinkUi(
    val href: String,
    val type: String,
)

fun Feeds.toFeedsUi(): FeedsUi {
    return FeedsUi(
        timelineUrl = timelineUrl,
        userUrl = userUrl,
        repositoryDiscussionsUrl = repositoryDiscussionsUrl,
        repositoryDiscussionsCategoryUrl = repositoryDiscussionsCategoryUrl,
        securityAdvisoriesUrl = securityAdvisoriesUrl,
        linksUi = links.toLinksUi(),
    )
}

fun Links.toLinksUi(): LinksUi {
    return LinksUi(
        timeLine = timeLine.toLinkUi(),
        user = user.toLinkUi(),
        repositoryDiscussions = repositoryDiscussions.toLinkUi(),
        repositoryDiscussionsCategory = repositoryDiscussionsCategory.toLinkUi(),
        securityAdvisories = securityAdvisories.toLinkUi()
    )
}

fun Link.toLinkUi(): LinkUi {
    return LinkUi(
        href = href,
        type = type
    )
}