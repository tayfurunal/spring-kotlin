package com.tayfurunal.springkotlin.exception

import org.springframework.http.HttpStatus

open class SpringKotlinException(exceptionMessage: String, val httpStatus: HttpStatus) : RuntimeException(exceptionMessage)