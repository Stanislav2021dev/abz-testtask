package com.abzagency.features.users.domain.repository

import androidx.paging.PagingData
import com.abzagency.features.users.models.domain.UserDomainModel
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    fun getUsersFromRemote(): Flow<PagingData<UserDomainModel>>
}