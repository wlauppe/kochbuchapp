package de.psekochbuch.exzellenzkoch.datalayer.remote.mapper

import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.UserDto
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User

class UserDtoEntityMapper() : EntityMapper<User,UserDto>(){
    override fun toEntity(dto: UserDto): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toDto(entitiy: User): UserDto {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
//die entities passen irgendwie nicht so mit den dtos überein
}