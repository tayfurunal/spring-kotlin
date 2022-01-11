package com.tayfurunal.springkotlin.model.response

data class UserDto(
    val id: Long?,
    val name: String,
    val surname: String,
    val email: String
)
