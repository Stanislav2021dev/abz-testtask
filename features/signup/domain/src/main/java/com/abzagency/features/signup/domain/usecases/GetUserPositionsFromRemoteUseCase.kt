package com.abzagency.features.signup.domain.usecases

import com.abzagency.features.signup.domain.repository.SignUpRepository
import javax.inject.Inject

class GetUserPositionsFromRemoteUseCase @Inject constructor(private val repository: SignUpRepository) {
     suspend operator fun invoke() = repository.getUserPositions()
}