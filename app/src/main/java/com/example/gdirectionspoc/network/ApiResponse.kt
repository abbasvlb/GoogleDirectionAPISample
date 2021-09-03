package com.example.gdirectionspoc.network

sealed class ApiResponse<out T> {
    data class Success<out T>(val value: T) : ApiResponse<T>()
    data class Error(val error: String? = null): ApiResponse<Nothing>()
    data class Loading(val value: String? = null): ApiResponse<Nothing>()

}
