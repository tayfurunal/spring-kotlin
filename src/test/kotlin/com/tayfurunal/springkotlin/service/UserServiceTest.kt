package com.tayfurunal.springkotlin.service

import com.tayfurunal.springkotlin.converter.UserConverter
import com.tayfurunal.springkotlin.helper.mockCreateUserRequest
import com.tayfurunal.springkotlin.helper.mockUser
import com.tayfurunal.springkotlin.helper.mockUserDto
import com.tayfurunal.springkotlin.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
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

    @Test
    fun `it should get all user`() {
        //given
        val mockUser = mockUser()
        val mockUserDto = mockUserDto()
        `when`(userRepository.findAll()).thenReturn(listOf(mockUser))
        `when`(userConverter.toDto(mockUser)).thenReturn(mockUserDto)

        //when
        val result = userService.getAll()

        //then
        assertThat(result.size).isEqualTo(1)
        assertThat(result).containsExactly(mockUserDto)
        verify(userRepository).findAll()
        verify(userConverter).toDto(mockUser)
        verifyNoMoreInteractions(userRepository, userConverter)
    }
}