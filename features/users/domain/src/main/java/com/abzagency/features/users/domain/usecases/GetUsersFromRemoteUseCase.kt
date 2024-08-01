package com.abzagency.features.users.domain.usecases

import com.abzagency.features.users.domain.repository.UsersRepository
import javax.inject.Inject

class GetUsersFromRemoteUseCase @Inject constructor(private val repository: UsersRepository) {
     operator fun invoke() = repository.getUsersFromRemote()
}