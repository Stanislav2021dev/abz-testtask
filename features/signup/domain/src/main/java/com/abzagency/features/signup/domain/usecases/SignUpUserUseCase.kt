package com.abzagency.features.signup.domain.usecases

import com.abzagency.features.signup.domain.repository.SignUpRepository
import com.abzagency.features.signup.models.domain.SignUpDomainModel
import javax.inject.Inject

class SignUpUserUseCase @Inject constructor(private val repository: SignUpRepository) {
    suspend operator fun invoke(token: String, signUpData: SignUpDomainModel) =
        repository.signupUser(
            token = token,
            signUpData = signUpData
        )
}