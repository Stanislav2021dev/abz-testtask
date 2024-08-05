package com.abzagency.core.common.paging

import androidx.paging.PagingSource
import com.abzagency.core.common.response.ErrorData
import com.abzagency.core.common.response.Response

abstract class RemotePagingSource<T : Any> : PagingSource<Int, T>() {
    suspend fun handleResponse(
        response: suspend () -> Response<List<T>>,
        success: suspend (data: List<T>) -> LoadResult<Int, T>,
        error: suspend (error: ErrorData) -> LoadResult<Int, T>
    ): LoadResult<Int, T> {
        return when (val result = response()) {
            is Response.Success -> {
                success(result.data)
            }

            is Response.Error -> {
                error(result.error)
            }
        }
    }
}