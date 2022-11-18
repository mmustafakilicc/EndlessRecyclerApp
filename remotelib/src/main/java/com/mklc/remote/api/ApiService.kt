package com.mklc.remote.api

import com.mklc.remote.data.model.response.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/public/v2/users")
    fun loadUsers(): Single<List<UserResponse>>

    @GET("/public/v2/users")
    fun loadUsers(@Query("page") page: Int, @Query("per_page") perPage: Int = 10): Single<List<UserResponse>>

}