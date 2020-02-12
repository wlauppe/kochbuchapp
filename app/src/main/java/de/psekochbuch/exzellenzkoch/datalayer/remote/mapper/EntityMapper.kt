package de.psekochbuch.exzellenzkoch.datalayer.remote.mapper

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

abstract class EntityMapper<Entity,Dto> {
    abstract fun toEntity(dto: Dto) : Entity
    abstract fun toDto(entitiy: Entity) : Dto
    //fun toListEntity(dto: List<Dto>) : List<Entity> = List.(dto, ::toEntity)
    fun toLiveEntity(dto: LiveData<Dto>) : LiveData<Entity> = Transformations.map(dto, ::toEntity)
    fun toLiveDto(entity: LiveData<Entity>) : LiveData<Dto> = Transformations.map(entity, ::toDto)
}

