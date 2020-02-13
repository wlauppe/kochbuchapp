package de.psekochbuch.exzellenzkoch.datalayer.remote.mapper

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

abstract class EntityMapper<Entity,Dto> {

    abstract fun toEntity(dto: Dto) : Entity

    abstract fun toDto(entitiy: Entity) : Dto

    fun toListEntity(dto: List<Dto>) : List<Entity> {
        return dto.map(::toEntity)
    }

    fun toListDto(entity: List<Entity>) : List<Dto>{
        return entity.map(::toDto)
    }

    fun toLiveEntity(dto: LiveData<Dto>) : LiveData<Entity>{
        return Transformations.map(dto, ::toEntity)
    }

    fun toLiveDto(entity: LiveData<Entity>) : LiveData<Dto>{
        return Transformations.map(entity, ::toDto)
    }

    fun toLiveListEntity(dto: LiveData<List<Dto>>) : LiveData<List<Entity>>{
        return Transformations.map(dto, ::toListEntity)
    }

    fun toLiveListDto(entity: LiveData<List<Entity>>) : LiveData<List<Dto>>{
        return Transformations.map(entity, ::toListDto)
    }

}

