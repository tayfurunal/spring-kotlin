package com.tayfurunal.springkotlin.service

import com.tayfurunal.springkotlin.converter.UserConverter
import com.tayfurunal.springkotlin.exception.SpringKotlinNotFoundException
import com.tayfurunal.springkotlin.helper.mockCreateUserRequest
import com.tayfurunal.springkotlin.helper.mockUser
import com.tayfurunal.springkotlin.helper.mockUserDto
import com.tayfurunal.springkotlin.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.verifyNoInteractions
import org.mockito.BDDMockito.verifyNoMoreInteractions
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

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
    fun `it should find all user`() {
        //given
        val mockUser = mockUser()
        val mockUserDto = mockUserDto()
        `when`(userRepository.findAll()).thenReturn(listOf(mockUser))
        `when`(userConverter.toDto(mockUser)).thenReturn(mockUserDto)

        //when
        val result = userService.findAll()

        //then
        assertThat(result.size).isEqualTo(1)
        assertThat(result).containsExactly(mockUserDto)
        verify(userRepository).findAll()
        verify(userConverter).toDto(mockUser)
        verifyNoMoreInteractions(userRepository, userConverter)
    }

    @Test
    fun `it should find user by id`() {
        //given
        val mockUser = mockUser()
        val mockUserDto = mockUserDto()
        `when`(userRepository.findById(25)).thenReturn(Optional.of(mockUser))
        `when`(userConverter.toDto(mockUser)).thenReturn(mockUserDto)

        //when
        val result = userService.findById(25)

        //then
        assertThat(result).isEqualTo(mockUserDto)
        verify(userRepository).findById(25)
        verify(userConverter).toDto(mockUser)
        verifyNoMoreInteractions(userRepository, userConverter)
    }

    @Test
    fun `it should throw when user not found`() {
        //given
        `when`(userRepository.findById(25)).thenReturn(Optional.empty())

        //when
        val throwable = catchThrowable { userService.findById(25) }

        //then )
        assertThat(throwable is SpringKotlinNotFoundException).isTrue
        assertThat((throwable as SpringKotlinNotFoundException).message).isEqualTo("User not found by 25")
        verify(userRepository).findById(25)
        verifyNoMoreInteractions(userRepository)
        verifyNoInteractions(userConverter)
    }
}