package com.tayfurunal.springkotlin.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tayfurunal.springkotlin.helper.mockCreateUserRequest
import com.tayfurunal.springkotlin.helper.mockUserDto
import com.tayfurunal.springkotlin.service.UserService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
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
        val resultActions = mockMvc.perform(
            post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(mockCreateUserRequest))
        )

        //then
        resultActions.andExpect(status().isCreated)
        verify(userService).create(mockCreateUserRequest)
        verifyNoMoreInteractions(userService)
    }

    @Test
    fun `it should create user when name is not more than 1 char`() {
        //given
        val mockCreateUserRequest = mockCreateUserRequest(name = "t", surname = "test surname", email = "test@test.com")

        //when
        val resultActions = mockMvc.perform(
            post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(mockCreateUserRequest))
        )

        //then
        resultActions
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.errors.[0]").value("Your name must be at least 2 characters."))
        verifyNoInteractions(userService)
    }

    @Test
    fun `it should find all`() {
        //given
        val mockUserDto = mockUserDto()
        `when`(userService.findAll()).thenReturn(listOf(mockUserDto))

        //when
        val resultActions = mockMvc.perform(get("/users"))

        //then
        resultActions
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.[0].id").value(1))
            .andExpect(jsonPath("$.[0].name").value("Tayfur"))
            .andExpect(jsonPath("$.[0].surname").value("Ünal"))
            .andExpect(jsonPath("$.[0].email").value("mtayfurunal@gmail.com"))
        verify(userService).findAll()
        verifyNoMoreInteractions(userService)
    }

    @Test
    fun `it should find by id`() {
        //given
        val mockUserDto = mockUserDto()
        `when`(userService.findById(1)).thenReturn(mockUserDto)

        //when
        val resultActions = mockMvc.perform(get("/users/1"))

        //then
        resultActions
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Tayfur"))
            .andExpect(jsonPath("$.surname").value("Ünal"))
            .andExpect(jsonPath("$.email").value("mtayfurunal@gmail.com"))
        verify(userService).findById(1)
        verifyNoMoreInteractions(userService)
    }
}