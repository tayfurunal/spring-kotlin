package com.tayfurunal.springkotlin.service

import com.tayfurunal.springkotlin.converter.UserConverter
import com.tayfurunal.springkotlin.helper.mockCreateUserRequest
import com.tayfurunal.springkotlin.helper.mockUser
import com.tayfurunal.springkotlin.repository.UserRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.verifyNoMoreInteractions
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class UserServiceTest(
    @Mock private val userRepository: UserRepository,
    @Mock private val userConverter: UserConverter
) {

    @InjectMocks
    lateinit var userService: UserService

    @Test
    fun `it should save user`() {
        //given
        val createUserRequest = mockCreateUserRequest()
        val mockUser = mockUser()
        `when`(userConverter.toEntity(createUserRequest)).thenReturn(mockUser)

        //when
        userService.create(createUserRequest)

        //then
        verify(userRepository).save(mockUser)
        verify(userConverter).toEntity(createUserRequest)
        verifyNoMoreInteractions(userRepository, userConverter)
    }
}