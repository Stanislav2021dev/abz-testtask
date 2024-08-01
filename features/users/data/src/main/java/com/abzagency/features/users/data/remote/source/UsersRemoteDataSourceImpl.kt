package com.abzagency.features.users.data.remote.source

import com.abzagency.core.common.response.Response
import com.abzagency.core.network.utils.executeRequest
import com.abzagency.features.users.data.remote.api.UsersApi
import com.abzagency.features.users.models.remote.UserWrapperRemoteModel
import javax.inject.Inject

internal class UsersRemoteDataSourceImpl @Inject constructor(
    private val api: UsersApi
) : UsersRemoteDataSource {
    override suspend fun getUsers(page: Int, count: Int): Response<UserWrapperRemoteModel> =
        executeRequest {
            api.getUsers(
                page = page,
                count = count
            )
        }
}