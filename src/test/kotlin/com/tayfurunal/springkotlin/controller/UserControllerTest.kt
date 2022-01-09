package com.tayfurunal.springkotlin.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tayfurunal.springkotlin.helper.mockCreateUserRequest
import com.tayfurunal.springkotlin.service.UserService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [UserController::class])
class UserControllerTest(@Autowired private val mockMvc: MockMvc) {

    @MockBean
    private lateinit var userService: UserService

    private val objectMapper = ObjectMapper()

    @Test
    fun `it should create user`() {
        //given
        val mockCreateUserRequest = mockCreateUserRequest()

        //when
        val resultActions = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(mockCreateUserRequest))
        )

        //then
        resultActions.andExpect(status().isCreated)
        verify(userService).create(mockCreateUserRequest)
        verifyNoMoreInteractions(userService)
    }
}