package com.ernisernis.githubfeed.github.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedsDto(
    @SerialName("timeline_url") val timelineUrl: String,
    @SerialName("user_url") val userUrl: String,
    @SerialName("repository_discussions_url") val repositoryDiscussionsUrl: String,
    @SerialName("repository_discussions_category_url") val repositoryDiscussionsCategoryUrl: String,
    @SerialName("security_advisories_url") val securityAdvisoriesUrl: String,
    @SerialName("_links") val links: LinksDto
)

@Serializable
data class LinksDto(
    @SerialName("timeline") val timeline: LinkDto,
    @SerialName("user") val user: LinkDto,
    @SerialName("repository_discussions") val repositoryDiscussions: LinkDto,
    @SerialName("repository_discussions_category") val repositoryDiscussionsCategory: LinkDto,
    @SerialName("security_advisories") val securityAdvisories: LinkDto
)

@Serializable
data class LinkDto(
    @SerialName("href") val href: String,
    @SerialName("type") val type: String,
)