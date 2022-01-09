package com.tayfurunal.springkotlin.controller

import com.tayfurunal.springkotlin.model.CreateUserRequest
import com.tayfurunal.springkotlin.model.UserDto
import com.tayfurunal.springkotlin.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid createUserRequest: CreateUserRequest) = userService.create(createUserRequest)

    @GetMapping
    fun getAll(): List<UserDto> = userService.getAll()
}