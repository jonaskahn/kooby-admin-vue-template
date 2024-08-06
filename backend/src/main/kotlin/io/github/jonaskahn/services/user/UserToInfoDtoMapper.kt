package io.github.jonaskahn.services.user

import io.github.jonaskahn.entities.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

@Mapper
interface UserToInfoDtoMapper {
    companion object {
        var INSTANCE: UserToInfoDtoMapper = Mappers.getMapper(UserToInfoDtoMapper::class.java)
    }

    @Mapping(target = "statusName", ignore = true)
    fun userToUserInfoDto(user: User): UserDto
}