package com.abzagency.features.users.domain.usecases

import com.abzagency.features.users.domain.repository.SignUpRepository
import javax.inject.Inject

class GetUserPositionsFromRemoteUseCase @Inject constructor(private val repository: SignUpRepository) {
     suspend operator fun invoke() = repository.getUserPositions()
}