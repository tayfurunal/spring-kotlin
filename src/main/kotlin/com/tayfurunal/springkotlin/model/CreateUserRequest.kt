package com.tayfurunal.springkotlin.model

data class CreateUserRequest(
    val name: String,
    val surname: String,
    val email: String
)