package com.tayfurunal.springkotlin.model.response

data class ErrorResponse(
    val exception: String,
    val timestamp: Long,
    val errors: List<String?>
)