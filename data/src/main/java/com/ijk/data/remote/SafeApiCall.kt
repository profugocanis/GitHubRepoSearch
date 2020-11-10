package com.ijk.data.remote

import com.ijk.domain.entity.RestResult
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): RestResult<T> {
    return try {
        val res = apiCall.invoke()
        if (res.errorBody() != null) {
            res.errorBody()
            val errorMessage = JSONObject(res.errorBody()?.string()).getString("message")
            return RestResult.GenericError(Exception(errorMessage))
        }
        val body = apiCall.invoke().body()
        RestResult.Success(body!!)
    } catch (throwable: Exception) {
        when (throwable) {
            is IOException -> RestResult.NetworkError
            is HttpException -> {
                RestResult.GenericError(throwable)
            }
            else -> {
                RestResult.GenericError(throwable)
            }
        }
    }
}