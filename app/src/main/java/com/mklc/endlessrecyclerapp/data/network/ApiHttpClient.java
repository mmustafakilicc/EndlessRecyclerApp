package com.mklc.endlessrecyclerapp.data.network;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiHttpClient {

    public static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(new MyInterceptor());
        return builder.build();
    }

    private static class MyInterceptor implements Interceptor {

        @NonNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("Content-type", "application/json")
                    .build();
            return chain.proceed(request);
        }
    }
}
