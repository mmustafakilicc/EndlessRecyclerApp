package com.mklc.endlessrecyclerapp.data.network;

import com.mklc.endlessrecyclerapp.data.model.network.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("users")
    Call<List<User>> getUsers(@Query("page") int page);
}
