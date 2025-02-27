package com.ernisernis.githubfeed.github.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ernisernis.githubfeed.github.data.dto.LinksDto

@Entity
data class FeedsEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val timelineUrl: String?,
    val userUrl: String?,
    val repositoryDiscussionsUrl: String?,
    val repositoryDiscussionsCategoryUrl: String?,
    val securityAdvisoriesUrl: String?,
    val linksDto: LinksDto?,
)