package com.example.ass_ph31495.model

data class ApiResponse<T>(
val status: Int,
val messenger: String,
val data: T
)

