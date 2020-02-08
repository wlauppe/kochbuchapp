package de.psekochbuch.exzellenzkoch.datalayer.remote.mapper

import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.UserDto
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User

class UserDtoEntityMapper() : EntityMapper<User,UserDto>(){
    override fun toEntity(dto: UserDto): User {
        return User(dto.userId,dto.imageUrl,dto.description)
    }

    override fun toDto(entitiy: User): UserDto {
        return UserDto(entitiy.userId,entitiy.imgUrl,entitiy.description)

    }
//die entities passen irgendwie nicht so mit den dtos überein
}