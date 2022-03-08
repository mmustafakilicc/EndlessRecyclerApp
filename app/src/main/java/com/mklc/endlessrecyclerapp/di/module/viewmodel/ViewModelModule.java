package com.mklc.endlessrecyclerapp.di.module.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mklc.endlessrecyclerapp.di.module.viewmodel.ViewModelFactory;
import com.mklc.endlessrecyclerapp.di.module.viewmodel.ViewModelKey;
import com.mklc.endlessrecyclerapp.ui.main.UserViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    protected abstract ViewModel userViewModel(UserViewModel userViewModel);
}
