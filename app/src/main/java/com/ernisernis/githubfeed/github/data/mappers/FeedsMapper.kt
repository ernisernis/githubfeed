package com.ernisernis.githubfeed.github.data.mappers

import com.ernisernis.githubfeed.github.data.dto.FeedsDto
import com.ernisernis.githubfeed.github.data.dto.LinkDto
import com.ernisernis.githubfeed.github.data.dto.LinksDto
import com.ernisernis.githubfeed.github.domain.Feeds
import com.ernisernis.githubfeed.github.domain.Link
import com.ernisernis.githubfeed.github.domain.Links


fun FeedsDto.toFeeds(): Feeds {
    return Feeds(
        timelineUrl = timelineUrl,
        userUrl = userUrl,
        repositoryDiscussionsUrl = repositoryDiscussionsUrl,
        repositoryDiscussionsCategoryUrl = repositoryDiscussionsCategoryUrl,
        securityAdvisoriesUrl = securityAdvisoriesUrl,
        links = links.toLinks()
    )
}

fun LinksDto.toLinks(): Links {
    return Links(
        timeLine = timeline.toLink(),
        user = user.toLink(),
        repositoryDiscussions = repositoryDiscussions.toLink(),
        repositoryDiscussionsCategory = repositoryDiscussionsCategory.toLink(),
        securityAdvisories = securityAdvisories.toLink()
    )
}

fun LinkDto.toLink(): Link {
    return Link(
        href = href,
        type = type,
    )
}