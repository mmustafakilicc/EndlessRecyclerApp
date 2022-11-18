package com.mklc.endlessrecyclerapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mklc.endlessrecyclerapp.data.UserDto
import com.mklc.remote.data.model.response.UserResponse
import com.mklc.remote.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _isLoading = MutableLiveData(false)

    private val userList = mutableListOf<UserDto>()
    val liveDataUserList = MutableLiveData<List<UserDto>>()
    val liveDataStopListLoading = MutableLiveData(false)

    fun loadUsers(page: Int){
        if(page > 4){ // check if it will really stop
            stopLoading()
            return
        }
        userRepository.loadUsers(page).subscribe({
           setupUsers(it)
        },{
            stopLoading()
            Timber.e(it)
        })
    }

    private fun setupUsers(response: List<UserResponse>){
        userList.addAll(response.map { user -> UserDto(user.id, user.name, user.email, user.status) })

        liveDataUserList.postValue(userList.toList())
    }

    fun isLoading() = _isLoading

    private fun stopLoading(){
        liveDataStopListLoading.postValue(true)
    }
}