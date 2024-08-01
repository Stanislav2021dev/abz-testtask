package com.abzagency.features.users.data.remote.api

import com.abzagency.features.users.models.remote.UserWrapperRemoteModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface UsersApi {
    @GET("api/v1/users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("count") count: Int
    ): Response<UserWrapperRemoteModel>

}