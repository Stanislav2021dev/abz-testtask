package com.abzagency.features.users.data.di

import com.abzagency.features.users.data.remote.source.UsersRemoteDataSourceImpl
import com.abzagency.features.users.domain.repository.UsersRepository
import com.abzagency.features.users.data.remote.api.UsersApi
import com.abzagency.features.users.data.remote.source.UsersRemoteDataSource
import com.abzagency.features.users.data.repository.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal interface UsersHiltModule {
    @Binds
    fun bindUsersRemoteDataSource(
        usersRemoteDataSourceImpl: UsersRemoteDataSourceImpl
    ): UsersRemoteDataSource

    @Binds
    fun bindUsersRepository(usersRepositoryImpl: UsersRepositoryImpl): UsersRepository

    companion object {
        @Provides
        fun provideUsersApi(retrofit: Retrofit): UsersApi =
            retrofit.create(UsersApi::class.java)
    }
}