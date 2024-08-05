package com.abzagency.features.users.data.remote.source.paging

import androidx.paging.PagingState
import com.abzagency.core.common.paging.RemotePagingSource
import com.abzagency.core.common.response.map
import com.abzagency.features.users.data.remote.source.UsersRemoteDataSource
import com.abzagency.features.users.models.remote.UserRemoteModel
import javax.inject.Inject

internal class UsersPagingRemoteDataSourceImpl @Inject constructor(
    private val remoteDataSource: UsersRemoteDataSource
) : RemotePagingSource<UserRemoteModel>() {
    override fun getRefreshKey(state: PagingState<Int, UserRemoteModel>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserRemoteModel> {
        val page = params.key ?: 1

        return handleResponse(
            response = {
                remoteDataSource.getUsers(
                    page = page,
                    count = params.loadSize
                ).map {
                    it.users
                }
            },
            success = { list ->
                val isEndOfPagination = list.size < params.loadSize

                LoadResult.Page(
                    data = list,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (isEndOfPagination) null else page + 1
                )
            },
            error = { error ->
                LoadResult.Error(error)
            }
        )
    }
}