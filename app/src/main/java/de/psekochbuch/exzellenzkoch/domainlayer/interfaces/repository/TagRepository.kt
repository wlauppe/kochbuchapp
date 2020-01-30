package de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository

import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.TagList

interface TagRepository {
    fun getTags(): TagList
}