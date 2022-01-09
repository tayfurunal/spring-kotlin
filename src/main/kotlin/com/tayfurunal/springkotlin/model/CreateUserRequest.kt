package com.tayfurunal.springkotlin.model

import javax.validation.constraints.NotEmpty

data class CreateUserRequest(
    val name: String,
    val surname: String,
    @NotEmpty(message = "test")
    val email: String
)