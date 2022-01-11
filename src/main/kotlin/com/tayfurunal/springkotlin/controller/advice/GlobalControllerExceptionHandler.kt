package com.tayfurunal.springkotlin.controller.advice

import com.tayfurunal.springkotlin.exception.SpringKotlinException
import com.tayfurunal.springkotlin.exception.SpringKotlinNotFoundException
import com.tayfurunal.springkotlin.model.response.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerExceptionHandler {


    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception?): ResponseEntity<ErrorResponse> {
        log.error("Generic exception occurred.", exception)
        val errorResponse = ErrorResponse("GenericException", System.currentTimeMillis(), listOf("Generic exception occurred."))
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(SpringKotlinException::class)
    fun handle(exception: SpringKotlinException): ResponseEntity<ErrorResponse> {
        log.error("SpringKotlinException occurred: ", exception)
        val errorResponse = ErrorResponse("SpringKotlinException", System.currentTimeMillis(), listOf(exception.message))
        return ResponseEntity(errorResponse, exception.httpStatus)
    }

    @ExceptionHandler(SpringKotlinNotFoundException::class)
    fun handle(exception: SpringKotlinNotFoundException): ResponseEntity<ErrorResponse> {
        log.error("SpringKotlinNotFoundException occurred: ", exception)
        val errorResponse = ErrorResponse("SpringKotlinNotFoundException", System.currentTimeMillis(), listOf(exception.message))
        return ResponseEntity(errorResponse, exception.httpStatus)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handle(exception: MethodArgumentNotValidException): ResponseEntity<ErrorResponse?>? {
        log.error("MethodArgumentNotValidException occurred.", exception)
        val errorMessages = exception.bindingResult.fieldErrors.map { fieldError: FieldError? -> fieldError?.defaultMessage }
        val errorResponse = ErrorResponse("MethodArgumentNotValidException", System.currentTimeMillis(), errorMessages)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}

