package com.abzagency.features.users.data.remote.source

import com.abzagency.core.common.response.Response
import com.abzagency.features.users.models.remote.UserWrapperRemoteModel

internal interface UsersRemoteDataSource {
    suspend fun getUsers(
        page: Int,
        count: Int
    ): Response<UserWrapperRemoteModel>
}