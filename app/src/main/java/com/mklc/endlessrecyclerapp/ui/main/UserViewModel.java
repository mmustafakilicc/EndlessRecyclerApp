package com.mklc.endlessrecyclerapp.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mklc.endlessrecyclerapp.data.model.network.User;
import com.mklc.endlessrecyclerapp.data.network.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    private final UserRepository userRepository;

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        userRepository.loadUsers(page).enqueue(new Callback<List<User>>() {
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
