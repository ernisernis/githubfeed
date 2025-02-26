package com.ernisernis.githubfeed.github.data.mappers

import com.ernisernis.githubfeed.github.data.database.entities.FeedsEntity
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

fun Feeds.toFeedsEntity(): FeedsEntity {
    return FeedsEntity(
        timelineUrl = timelineUrl,
        userUrl = userUrl,
        repositoryDiscussionsUrl = repositoryDiscussionsUrl,
        repositoryDiscussionsCategoryUrl = repositoryDiscussionsCategoryUrl,
        securityAdvisoriesUrl = securityAdvisoriesUrl,
        linksDto = links.toLinksDto()
    )
}

fun Links.toLinksDto(): LinksDto {
    return LinksDto(
        timeline = timeLine.toLinkDto(),
        user = user.toLinkDto(),
        repositoryDiscussions = repositoryDiscussions.toLinkDto(),
        repositoryDiscussionsCategory = repositoryDiscussionsCategory.toLinkDto(),
        securityAdvisories = securityAdvisories.toLinkDto()
    )
}

fun Link.toLinkDto(): LinkDto {
    return LinkDto(
        href = href,
        type = type
    )
}