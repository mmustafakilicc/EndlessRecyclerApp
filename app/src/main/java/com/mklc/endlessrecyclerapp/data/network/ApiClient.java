package com.mklc.endlessrecyclerapp.data.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiClient {

    @Singleton
    @Provides
    public ApiService getApiService(ApiHttpClient apiHttpClient, ApiInterceptor apiInterceptor) {
        return new retrofit2.Retrofit.Builder()
                .baseUrl("https://gorest.co.in/public/v2/")
                .client(apiHttpClient.getClient(apiInterceptor))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build().create(ApiService.class);
    }

//    @Singleton
//    @Provides
//    OkHttpClient getOkHttpClient(ApiInterceptor myInterceptor) {
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.interceptors().add(myInterceptor);
//        return builder.build();
//    }
}
