package com.mklc.endlessrecyclerapp.data.network.repository;

import com.mklc.endlessrecyclerapp.data.model.network.User;
import com.mklc.endlessrecyclerapp.data.network.ApiService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class UserRepository {

    private final ApiService apiService;

    @Inject
    public UserRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Call<List<User>> loadUsers(int page) {
        return apiService.getUsers(page);
    }
}
