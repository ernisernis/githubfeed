package com.ernisernis.githubfeed.github.domain

data class Feeds(
    val timelineUrl: String?,
    val userUrl: String?,
    val repositoryDiscussionsUrl: String?,
    val repositoryDiscussionsCategoryUrl: String?,
    val securityAdvisoriesUrl: String?,
    val links: Links?,
)

data class Links(
    val timeLine: Link?,
    val user: Link?,
    val repositoryDiscussions: Link?,
    val repositoryDiscussionsCategory: Link?,
    val securityAdvisories: Link?
)

data class Link(
    val href: String?,
    val type: String?,
)
