package com.abzagency.features.users.domain.repository

import com.abzagency.core.common.response.Response
import com.abzagency.features.users.models.domain.PositionDomainModel
import com.abzagency.features.users.models.domain.SignUpDomainModel

interface SignUpRepository {
    suspend fun getUserPositions(): Response<List<PositionDomainModel>>
    suspend fun getUserToken() : Response<String>
    suspend fun signupUser(token: String, signUpData: SignUpDomainModel): Response<Unit>
}