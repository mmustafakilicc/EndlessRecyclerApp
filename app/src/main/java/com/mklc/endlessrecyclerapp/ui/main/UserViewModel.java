package com.mklc.endlessrecyclerapp.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mklc.endlessrecyclerapp.data.model.network.User;
import com.mklc.endlessrecyclerapp.data.network.ApiClient;
import com.mklc.endlessrecyclerapp.data.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends AndroidViewModel {

    private final ApiService apiService;

    public UserViewModel(@NonNull Application application) {
        super(application);
        apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
    }

    private MutableLiveData<List<User>> liveDataUserList;

    public MutableLiveData<List<User>> getLiveDataUserList() {
        if (liveDataUserList == null) {
            liveDataUserList = new MutableLiveData<>();
        }
        return liveDataUserList;
    }

    public void loadUsers(int page) {

        getLiveDataUserList().setValue(null);

        apiService.getUsers(page).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        getLiveDataUserList().setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                getLiveDataUserList().postValue(null);
            }
        });
    }
}
