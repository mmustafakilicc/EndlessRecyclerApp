package com.mklc.endlessrecyclerapp.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mklc.endlessrecyclerapp.R
import com.mklc.endlessrecyclerapp.databinding.ActivityUserListBinding
import com.mklc.endlessrecyclerapp.ui.adapter.AdapterViewData
import com.mklc.endlessrecyclerapp.ui.adapter.EndlessRecyclerViewAdapter
import com.mklc.endlessrecyclerapp.ui.adapter.Type
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListActivity : AppCompatActivity() {

    //region variables
    private val userViewModel: UserViewModel by viewModels()

    private lateinit var binding: ActivityUserListBinding
    private lateinit var adapter: EndlessRecyclerViewAdapter

    //endregion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list)
        binding.lifecycleOwner = this

        initialize()
    }

    private fun initialize() {
        adapter = EndlessRecyclerViewAdapter()
        binding.recyclerViewMAUserList.adapter = adapter
        binding.recyclerViewMAUserList.listenScrollCallBack {
            onScrollEnded(it)
        }
        observeItems()
        userViewModel.loadUsers(1)
    }

    private fun observeItems(){
        userViewModel.apply {
            liveDataUserList.observe(this@UserListActivity){
                val list = it.map { user -> AdapterViewData(id = user.id, item = user, type = Type.ITEM) }
                adapter.submitList(list.toMutableList())
            }
            liveDataStopListLoading.observe(this@UserListActivity){
                if(it){
                    adapter.stop()
                }
            }
        }
    }

    private fun onScrollEnded(page: Int){
        userViewModel.loadUsers(page)
    }
}