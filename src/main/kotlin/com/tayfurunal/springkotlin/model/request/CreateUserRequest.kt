package com.tayfurunal.springkotlin.model.request

import javax.validation.constraints.Size

data class CreateUserRequest(
    @field:Size(min = 2, message = "Your name must be at least 2 characters.")
    val name: String,
    val surname: String,
    val email: String
)