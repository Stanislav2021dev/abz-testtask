package com.abzagency.features.signup.data.repository

import com.abzagency.core.common.response.Response
import com.abzagency.core.common.response.map
import com.abzagency.features.signup.data.remote.source.SignUpRemoteDataSource
import com.abzagency.features.signup.domain.repository.SignUpRepository
import com.abzagency.features.signup.models.domain.PositionDomainModel
import com.abzagency.features.signup.models.domain.SignUpDomainModel
import com.abzagency.features.signup.models.remote.toDomainModel
import com.abzagency.features.signup.models.remote.toRemoteModel
import javax.inject.Inject

internal class SignUpRepositoryImpl @Inject constructor(
    private val signUpDataSource: SignUpRemoteDataSource
) : SignUpRepository {
    override suspend fun getUserPositions(): Response<List<PositionDomainModel>> =
        signUpDataSource.getPositions().map { positions ->
            positions.positions.map { it.toDomainModel() }
        }

    override suspend fun getUserToken(): Response<String> =
        signUpDataSource.getToken().map { it.token }

    override suspend fun signupUser(token: String, signUpData: SignUpDomainModel): Response<Unit> =
        signUpDataSource.signUp(
            token = token,
            signUpData = signUpData.toRemoteModel()
        )
}