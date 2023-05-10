package com.ldnhat.embedded_server.common.model


data class ApiResponse<T>(
    val code : String,

    val data : T
)
