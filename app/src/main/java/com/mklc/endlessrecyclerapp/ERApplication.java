package com.mklc.endlessrecyclerapp;

import android.app.Application;

import com.mklc.endlessrecyclerapp.di.component.ApplicationComponent;
import com.mklc.endlessrecyclerapp.di.component.DaggerApplicationComponent;

public class ERApplication extends Application {

    public ApplicationComponent applicationComponent = DaggerApplicationComponent.create();

}
