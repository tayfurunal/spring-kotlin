package com.tayfurunal.springkotlin.exception

import org.springframework.http.HttpStatus

class SpringKotlinNotFoundException(exceptionMessage: String) : SpringKotlinException(exceptionMessage, HttpStatus.NOT_FOUND)