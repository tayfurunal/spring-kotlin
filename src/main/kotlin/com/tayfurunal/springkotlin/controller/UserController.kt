package com.tayfurunal.springkotlin.controller

import com.tayfurunal.springkotlin.model.request.CreateUserRequest
import com.tayfurunal.springkotlin.model.response.UserDto
import com.tayfurunal.springkotlin.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid createUserRequest: CreateUserRequest) = userService.create(createUserRequest)

    @GetMapping
    fun findAll(): List<UserDto> = userService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): UserDto = userService.findById(id)
}