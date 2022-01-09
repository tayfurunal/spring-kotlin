package com.tayfurunal.springkotlin.converter

import com.tayfurunal.springkotlin.entity.User
import com.tayfurunal.springkotlin.model.CreateUserRequest
import com.tayfurunal.springkotlin.model.UserDto
import org.springframework.stereotype.Component

@Component
class UserConverter {

    fun toEntity(createUserRequest: CreateUserRequest): User {
        return User(
            name = createUserRequest.name,
            surname = createUserRequest.surname,
            email = createUserRequest.email
        )
    }

    fun toDto(user: User): UserDto {
        return UserDto(
            id = user.id,
            name = user.name,
            surname = user.surname,
            email = user.email
        )
    }
}