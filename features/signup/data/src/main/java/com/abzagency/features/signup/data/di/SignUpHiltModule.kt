package com.abzagency.features.signup.data.di

import com.abzagency.features.signup.data.remote.api.SignUpApi
import com.abzagency.features.signup.data.remote.source.SignUpRemoteDataSource
import com.abzagency.features.signup.data.remote.source.SignUpRemoteDataSourceImpl
import com.abzagency.features.signup.data.repository.SignUpRepositoryImpl
import com.abzagency.features.users.domain.repository.SignUpRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal interface SignUpHiltModule {
    @Binds
    fun bindSignUpRemoteDataSource(
        signUpRemoteDataSourceImpl: SignUpRemoteDataSourceImpl
    ): SignUpRemoteDataSource

    @Binds
    fun bindSignUpRepository(signUpRepositoryImpl: SignUpRepositoryImpl): SignUpRepository

    companion object {
        @Provides
        fun provideSignUpApi(retrofit: Retrofit): SignUpApi =
            retrofit.create(SignUpApi::class.java)
    }
}