package com.tayfurunal.springkotlin.helper

import com.tayfurunal.springkotlin.entity.User
import com.tayfurunal.springkotlin.model.CreateUserRequest

fun mockUser(
    id: Long = 1,
    name: String = "Tayfur",
    surname: String = "Ünal",
    email: String = "mtayfurunal@gmail.com"
) = User(id, name, surname, email)

fun mockCreateUserRequest(
    name: String = "Tayfur",
    surname: String = "Ünal",
    email: String = "mtayfurunal@gmail.com"
) = CreateUserRequest(name, surname, email)