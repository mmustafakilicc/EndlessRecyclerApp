package com.mklc.endlessrecyclerapp.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mklc.endlessrecyclerapp.data.model.network.User;
import com.mklc.endlessrecyclerapp.databinding.ActivityMainBinding;
import com.mklc.endlessrecyclerapp.ui.adapter.EndlessRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserViewModel userViewModel;
    private int currentPage = 1;
    private List<User> userList;
    private boolean isLoading;
    private EndlessRecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {

        userList = new ArrayList<>();
        adapter = new EndlessRecyclerViewAdapter(userList);
        linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerViewMAUserList.setLayoutManager(linearLayoutManager);
        binding.recyclerViewMAUserList.setAdapter(adapter);
        binding.recyclerViewMAUserList.addOnScrollListener(scrollListener);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getLiveDataUserList().observe(this, this::controlUserResult);
        loadMore(currentPage);

    }

    private void controlUserResult(List<User> userList) {
        if (userList != null && userList.size() > 0) {
            // loading ViewType will be moved to last index of list
            if(this.userList.size() > 0){
                this.userList.remove(this.userList.size() - 1);
            }
            this.userList.addAll(userList);
            this.userList.add(null);

            //new items were inserted
            int adapterItemCount = adapter.getItemCount();
            adapter.notifyItemRangeInserted(adapterItemCount, userList.size());

            isLoading = false;
            currentPage++;
            binding.progressIndicatorMA.setVisibility(View.INVISIBLE);
        }
    }

    private void loadMore(int currentPage) {
        userViewModel.loadUsers(currentPage);
    }

    private final RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (!isLoading) {
                isLoading = true;
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() >= adapter.getItemCount() - 1) {
                    loadMore(currentPage);
                }else {
                    isLoading = false;
                }
            }
        }
    };

}