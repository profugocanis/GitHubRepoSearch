package com.ijk.domain.entity

sealed class RestResult<out T> {
    data class Success<out T>(val value: T) : RestResult<T>()
    data class GenericError(val error: Exception? = null) : RestResult<Nothing>()
    object NetworkError : RestResult<Nothing>()
}