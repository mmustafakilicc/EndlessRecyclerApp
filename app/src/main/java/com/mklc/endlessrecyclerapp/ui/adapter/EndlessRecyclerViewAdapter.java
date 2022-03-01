package com.mklc.endlessrecyclerapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mklc.endlessrecyclerapp.data.model.network.User;
import com.mklc.endlessrecyclerapp.databinding.ItemLoaderBinding;
import com.mklc.endlessrecyclerapp.databinding.ItemUserBinding;
import com.mklc.endlessrecyclerapp.utils.GlobalEnums;

import java.util.List;

public class EndlessRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<User> userList;

    public EndlessRecyclerViewAdapter(@NonNull List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == GlobalEnums.AdapterViewType.VIEW_TYPE_DATA.getViewType()) {
            ItemUserBinding itemUserBinding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new UserViewHolder(itemUserBinding);
        }
        ItemLoaderBinding itemLoaderBinding = ItemLoaderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LoaderViewHolder(itemLoaderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserViewHolder) {
            ((UserViewHolder) holder).bind(userList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (userList.get(position) == null) {
            return GlobalEnums.AdapterViewType.VIEW_TYPE_LOAD.getViewType();
        }
        return GlobalEnums.AdapterViewType.VIEW_TYPE_DATA.getViewType();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        private final ItemUserBinding itemUserBinding;

        public UserViewHolder(ItemUserBinding itemUserBinding) {
            super(itemUserBinding.getRoot());
            this.itemUserBinding = itemUserBinding;
        }

        void bind(@NonNull User user) {
            itemUserBinding.textViewUIName.setText(user.getName());
            itemUserBinding.textViewUIEmail.setText(user.getEmail());
            itemUserBinding.textViewUIStatus.setText(user.getStatus());
        }
    }

    static class LoaderViewHolder extends RecyclerView.ViewHolder {

        public LoaderViewHolder(@NonNull ItemLoaderBinding itemLoaderBinding) {
            super(itemLoaderBinding.getRoot());
        }
    }
}
