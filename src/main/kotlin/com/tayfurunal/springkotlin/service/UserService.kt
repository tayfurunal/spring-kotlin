package com.tayfurunal.springkotlin.service

import com.tayfurunal.springkotlin.converter.UserConverter
import com.tayfurunal.springkotlin.exception.SpringKotlinNotFoundException
import com.tayfurunal.springkotlin.model.request.CreateUserRequest
import com.tayfurunal.springkotlin.model.response.UserDto
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

    fun findAll(): List<UserDto> {
        val users = userRepository.findAll()
        return users.map { userConverter.toDto(it) }
    }

    fun findById(id: Long): UserDto {
        val user = userRepository.findById(id).orElseThrow { SpringKotlinNotFoundException("User not found by $id") }
        return userConverter.toDto(user)
    }
}