package com.ernisernis.githubfeed.github.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FeedsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timelineUrl: String,
    val userUrl: String,
    val repositoryDiscussionsUrl: String,
    val repositoryDiscussionsCategoryUrl: String,
    val securityAdvisoriesUrl: String,
    val links: LinksEntity,
)

@Entity
data class LinksEntity(
    val timeLine: LinkEntity,
    val user: LinkEntity,
    val repositoryDiscussions: LinkEntity,
    val repositoryDiscussionsCategory: LinkEntity,
    val securityAdvisories: LinkEntity
)

@Entity
data class LinkEntity(
    val href: String,
    val type: String,
)
