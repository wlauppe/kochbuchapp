package de.psekochbuch.exzellenzkoch.datalayer.remote.mapper

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

/**
 * This class is used to switch between entities and dto
 */
abstract class EntityMapper<Entity,Dto> {

    /**
     * calculates an entity object from an dto object
     * @param dto: the entity object that should be converted
     * @return: the converted entity object
     */
    abstract fun toEntity(dto: Dto) : Entity

    /**
     * calculates an dto object from an entity object
     * @param entitiy: the entity object that should be converted
     * @return: the converted dto object
     */
    abstract fun toDto(entitiy: Entity) : Dto

    /**
     * calculates an list of entity object from an list of dto object
     * @param dto: the list of dto objects that should be converted
     * @return: the converted list of entity objects
     */
    fun toListEntity(dto: List<Dto>) : List<Entity> {
        return dto.map(::toEntity)
    }

    /**
     * calculates an list of dto object from an list of entity object
     * @param entity: the list of entity object that should be converted
     * @return: the converted list of dto object
     */
    fun toListDto(entity: List<Entity>) : List<Dto>{
        return entity.map(::toDto)
    }

    /**
     * calculates an entity object wrapped in livedata from an dto object wrapped in livedata
     * @param dto: the dto object wrapped in livedata that should be converted
     * @return: the converted entity object wrapped in livedata
     */
    fun toLiveEntity(dto: LiveData<Dto>) : LiveData<Entity>{
        return Transformations.map(dto, ::toEntity)
    }

    /**
     * calculates an dto object wrapped in livedata from an entity object wrapped in livedata
     * @param entity: the entity object wrapped in livedata that should be converted
     * @return: the converted dto object wrappe in livedata
     */
    fun toLiveDto(entity: LiveData<Entity>) : LiveData<Dto>{
        return Transformations.map(entity, ::toDto)
    }

    /**
     * calculates an list of entity object wrapped in livedata from an list of dto object wrapped in livedata
     * @param dto: the list of dto object wrapped in livedata that should be converted
     * @return: the converted list of entity object wrapped in livedata
     */
    fun toLiveListEntity(dto: LiveData<List<Dto>>) : LiveData<List<Entity>>{
        return Transformations.map(dto, ::toListEntity)
    }

    /**
     * calculates an list of dto object wrapped in livedata from an list of entity object wrapped in livedata
     * @param dto: the list of entity object wrapped in livedata that should be converted
     * @return: the converted list of dto object wrapped in livedata
     */
    fun toLiveListDto(entity: LiveData<List<Entity>>) : LiveData<List<Dto>>{
        return Transformations.map(entity, ::toListDto)
    }
}

