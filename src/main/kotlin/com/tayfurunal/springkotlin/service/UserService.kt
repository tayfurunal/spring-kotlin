package com.tayfurunal.springkotlin.service

import com.tayfurunal.springkotlin.converter.UserConverter
import com.tayfurunal.springkotlin.model.CreateUserRequest
import com.tayfurunal.springkotlin.model.UserDto
import com.tayfurunal.springkotlin.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userConverter: UserConverter
) {

    fun create(createUserRequest: CreateUserRequest) {
        val user = userConverter.toEntity(createUserRequest)
        userRepository.save(user)
    }

    fun getAll(): List<UserDto> {
        val users = userRepository.findAll()
        return users.map { userConverter.toDto(it) }
    }
}