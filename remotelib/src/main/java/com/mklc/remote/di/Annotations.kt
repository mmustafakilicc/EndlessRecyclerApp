package com.mklc.remote.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseInterceptorOkHttpclient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingInterceptorOkHttpclient