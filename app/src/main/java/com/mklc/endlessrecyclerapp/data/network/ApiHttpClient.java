package com.mklc.endlessrecyclerapp.data.network;

import androidx.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiHttpClient {

    @Inject
    protected ApiHttpClient(){}

    public OkHttpClient getClient(ApiInterceptor myInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(myInterceptor);
        return builder.build();
    }
}
