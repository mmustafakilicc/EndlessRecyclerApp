package com.mklc.remote.repository

import com.mklc.remote.api.ApiService
import com.mklc.remote.data.model.response.UserResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun loadUsers(): Single<List<UserResponse>> = apiService.loadUsers()

    fun loadUsers(page: Int): Single<List<UserResponse>> = apiService.loadUsers(page)
}