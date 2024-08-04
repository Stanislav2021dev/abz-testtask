package com.abzagency.features.signup.data.remote.api

import com.abzagency.features.users.models.remote.PositionsWrapperRemoteModel
import com.abzagency.features.users.models.remote.SignupRemoteModel
import com.abzagency.features.users.models.remote.UserTokenRemoteModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

internal interface SignUpApi {
    @GET("api/v1/positions")
    suspend fun getPositions(): Response<PositionsWrapperRemoteModel>

    @GET("api/v1/token")
    suspend fun getToken(): Response<UserTokenRemoteModel>

    @Multipart
    @POST("api/v1/users")
    suspend fun signUp(
        @Header("Token") token: String,
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("position_id") positionId: RequestBody,
        @Part photo: MultipartBody.Part
    ): Response<Unit>
}