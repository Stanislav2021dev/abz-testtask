package com.abzagency.features.signup.data.remote.source

import com.abzagency.core.common.response.Response
import com.abzagency.features.users.models.remote.PositionsWrapperRemoteModel
import com.abzagency.features.users.models.remote.SignupRemoteModel
import com.abzagency.features.users.models.remote.UserTokenRemoteModel

internal interface SignUpRemoteDataSource {
    suspend fun getPositions(): Response<PositionsWrapperRemoteModel>
    suspend fun getToken(): Response<UserTokenRemoteModel>
    suspend fun signUp(token: String, signUpData: SignupRemoteModel): Response<Unit>
}