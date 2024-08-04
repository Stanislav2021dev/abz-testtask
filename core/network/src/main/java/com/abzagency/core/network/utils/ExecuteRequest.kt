package com.abzagency.core.network.utils

import com.abzagency.core.common.response.ErrorCodes
import com.abzagency.core.common.response.ErrorData
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.Call
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import com.abzagency.core.common.response.Response as CommonResponse

suspend fun <T : Any> executeRequest(request: suspend () -> Response<T>): CommonResponse<T> = try {
    val response = request.invoke()
    when (response.isSuccessful) {
        true -> CommonResponse.Success(response.body() as T)
        else -> createErrorResponse(response)
    }
} catch (e: Exception) {
    createErrorFromException(e)
}

private fun mapJsonToErrorString(jsonObject: JsonObject): String {
    val message = jsonObject["message"]?.jsonPrimitive?.content.orEmpty()

    val failsObject = jsonObject["fails"]?.jsonObject ?: JsonObject(mapOf())
    val errors = mutableListOf<String>()
    failsObject.forEach { (_, value) ->
        val errorMessages = value as? JsonArray
        errorMessages?.forEach {
            errors.add(it.jsonPrimitive.content)
        }
    }

    return message + "\n" + errors.joinToString("\n")
}

private fun <T : Any> createErrorResponse(response: Response<T>): CommonResponse.Error<T> {
    val errorBody = response.errorBody()
    val errorData = if (errorBody != null) {
        val json: JsonObject = Json.decodeFromString(errorBody.string())
        val errors = mapJsonToErrorString(json)

        ErrorData(
            code = response.code(),
            message = errors
        )
    } else {
        ErrorData(
            code = response.code(),
            message = response.message()
        )
    }

    return CommonResponse.Error(
        errorData
    )
}

private fun <T : Any> createErrorFromException(e: Exception): CommonResponse.Error<T> {
    return when (e) {
        is SocketTimeoutException, is UnknownHostException, is ConnectException ->
            CommonResponse.Error(
                ErrorData(
                    code = ErrorCodes.INTERNET_CONNECTION_ERROR,
                    message = e.message
                )
            )

        is IllegalArgumentException -> {
            CommonResponse.Error(
                ErrorData(
                    code = ErrorCodes.JSON_VALIDATION_ERROR,
                    message = e.message
                )
            )
        }

        else -> {
            CommonResponse.Error(
                ErrorData(
                    code = ErrorCodes.UNKNOWN_ERROR,
                    message = e.message
                )
            )
        }
    }
}