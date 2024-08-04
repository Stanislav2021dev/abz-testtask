package com.abzagency.features.signup.data.remote.source

import android.util.Log
import com.abzagency.core.common.response.Response
import com.abzagency.core.network.utils.executeRequest
import com.abzagency.features.signup.data.remote.api.SignUpApi
import com.abzagency.features.users.models.remote.PositionsWrapperRemoteModel
import com.abzagency.features.users.models.remote.SignupRemoteModel
import com.abzagency.features.users.models.remote.UserTokenRemoteModel
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

internal class SignUpRemoteDataSourceImpl @Inject constructor(
    private val api: SignUpApi
) : SignUpRemoteDataSource {
    override suspend fun getPositions(): Response<PositionsWrapperRemoteModel> =
        executeRequest {
            api.getPositions()
        }

    override suspend fun getToken(): Response<UserTokenRemoteModel> =
        executeRequest {
            api.getToken()
        }

    override suspend fun signUp(token: String, signUpData: SignupRemoteModel): Response<Unit> =
        executeRequest {
            api.signUp(
                token = token,
                name = signUpData.name.toRequestBody(),
                email = signUpData.email.toRequestBody(),
                phone = signUpData.phone.toRequestBody(),
                positionId = signUpData.positionId.toString().toRequestBody(),
                photo = MultipartBody.Part.createFormData(
                    "photo", signUpData.photo.name, signUpData.photo.asRequestBody()
                )
            )
        }


}