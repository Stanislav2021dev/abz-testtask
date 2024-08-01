package com.abzagency.features.users.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.abzagency.features.users.data.remote.source.paging.UsersPagingRemoteDataSourceImpl
import com.abzagency.features.users.domain.repository.UsersRepository
import com.abzagency.features.users.models.domain.UserDomainModel
import com.abzagency.features.users.models.remote.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Provider

internal class UsersRepositoryImpl @Inject constructor(
    private val pagingRemoteDataSource: Provider<UsersPagingRemoteDataSourceImpl>
) : UsersRepository {
    private val usersRemotePagingConfig = PagingConfig(
        pageSize = PAGE_SIZE,
        initialLoadSize = INITIAL_LOAD_SIZE
    )

    override fun getUsersFromRemote(): Flow<PagingData<UserDomainModel>> {
        return Pager(usersRemotePagingConfig) {
            pagingRemoteDataSource.get()
        }.flow.map { pagingData ->
            pagingData.map { it.toDomainModel() }
        }
    }

    companion object {
        private const val PAGE_SIZE = 6
        private const val INITIAL_LOAD_SIZE = 6
    }
}