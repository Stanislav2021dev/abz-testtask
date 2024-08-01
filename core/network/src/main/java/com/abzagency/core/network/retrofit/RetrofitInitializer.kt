package com.abzagency.core.network.retrofit

import com.abzagency.core.network.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Dispatcher
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

internal object RetrofitInitializer {
    private const val DEFAULT_TIMEOUT = 60

    fun createGeneralRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(createKotlinJsonConvertor())
            .client(createGeneralOkHttpClient())
            .baseUrl(BuildConfig.BASE_URL1)
            .build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun createKotlinJsonConvertor(): Converter.Factory {
        val json = Json {
            explicitNulls = false
            ignoreUnknownKeys = true
        }
        return json.asConverterFactory("application/json".toMediaType())
    }

    private fun createGeneralOkHttpClient(): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        val builder = OkHttpClient.Builder()
            .callTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .dispatcher(dispatcher)
            .retryOnConnectionFailure(true)


        val httpLoggingInterceptor = HttpLoggingInterceptor()
        builder.addInterceptor(
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        )

        return builder.build()
    }
}