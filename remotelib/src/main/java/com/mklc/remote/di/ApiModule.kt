package com.mklc.remote.di

import com.mklc.remote.api.ApiService
import com.mklc.remote.api.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideApiService(
        @LoggingInterceptorOkHttpclient loggingHttpClient: OkHttpClient
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(loggingHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}