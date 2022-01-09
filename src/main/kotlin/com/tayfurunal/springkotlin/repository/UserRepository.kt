package com.tayfurunal.springkotlin.repository

import com.tayfurunal.springkotlin.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>
