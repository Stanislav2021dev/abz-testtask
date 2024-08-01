package com.abzagency.core.network.di

import com.abzagency.core.network.retrofit.RetrofitInitializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkHiltModule {
    @Singleton
    @Provides
    fun provideUnauthorizedOkHttpClient(): Retrofit = RetrofitInitializer.createGeneralRetrofitClient()

    @Singleton
    @Provides
    fun provideAuthorizedOkHttpClient(
    ): Retrofit = RetrofitInitializer.createGeneralRetrofitClient()
}