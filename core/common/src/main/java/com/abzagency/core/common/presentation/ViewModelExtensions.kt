package com.abzagency.core.common.presentation

import androidx.lifecycle.ViewModel
import com.abzagency.core.common.response.ErrorData
import com.abzagency.core.common.response.Response
import kotlinx.coroutines.delay

suspend fun <T : Any> performUseCase(
    useCase: suspend () -> Response<T>,
    success: suspend (data: T) -> Unit,
    error: suspend (error: ErrorData) -> Unit
) {
    when (val response = useCase()) {
        is Response.Success -> {
            success(response.data)
        }

        is Response.Error -> {
            error(response.error)
        }
    }
}

suspend inline fun loadingTask(setLoading: (isLoading: Boolean) -> Unit, block: () -> Unit) {
    try {
        setLoading(true)

        // Delay for shimmers displaying
        delay(1000)

        block()
    } finally {
        setLoading(false)
    }
}