package com.mklc.endlessrecyclerapp.di.component;

import com.mklc.endlessrecyclerapp.data.network.ApiClient;
import com.mklc.endlessrecyclerapp.di.module.viewmodel.ViewModelModule;
import com.mklc.endlessrecyclerapp.ui.base.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ViewModelModule.class, ApiClient.class})
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);
}
