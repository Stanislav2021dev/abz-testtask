package com.abzagency.features.signup.domain.repository

import com.abzagency.core.common.response.Response
import com.abzagency.features.signup.models.domain.PositionDomainModel
import com.abzagency.features.signup.models.domain.SignUpDomainModel

interface SignUpRepository {
    suspend fun getUserPositions(): Response<List<PositionDomainModel>>
    suspend fun getUserToken() : Response<String>
    suspend fun signupUser(token: String, signUpData: SignUpDomainModel): Response<Unit>
}