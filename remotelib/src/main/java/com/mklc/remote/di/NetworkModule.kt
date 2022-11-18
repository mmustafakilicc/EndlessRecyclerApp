package com.mklc.remote.di

import com.mklc.remote.api.interceptor.BaseInterceptor
import com.mklc.remote.api.interceptor.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseInterceptor(): BaseInterceptor{
        return BaseInterceptor()
    }

    @Provides
    fun provideLoggingInterceptor(): LoggingInterceptor{
        return LoggingInterceptor()
    }

    @BaseInterceptorOkHttpclient
    @Provides
    fun provideBaseInterceptorOkHttpClient(baseInterceptor: BaseInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(baseInterceptor)
            .build()
    }

    @LoggingInterceptorOkHttpclient
    @Provides
    fun provideLoggingInterceptorOkHttpClient(baseInterceptor: BaseInterceptor, loggingInterceptor: LoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(baseInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}