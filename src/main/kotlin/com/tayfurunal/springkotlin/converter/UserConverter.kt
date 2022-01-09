package com.tayfurunal.springkotlin.converter

import com.tayfurunal.springkotlin.entity.User
import com.tayfurunal.springkotlin.model.CreateUserRequest
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
}