package com.abzagency.core.common.presentation

import androidx.lifecycle.ViewModel
import com.abzagency.core.common.response.ErrorData
import com.abzagency.core.common.response.Response

suspend fun <T : Any> ViewModel.performUseCase(
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

inline fun ViewModel.loadingTask(setLoading: (isLoading: Boolean) -> Unit, block: () -> Unit) {
    try {
        setLoading(true)
        block()
    } finally {
        setLoading(false)
    }
}