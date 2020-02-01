package de.psekochbuch.exzellenzkoch.datalayer.remote.mapper

interface EntityMapper<Entity,Dto> {
    fun toEntity(dto: Dto) : Entity
}

