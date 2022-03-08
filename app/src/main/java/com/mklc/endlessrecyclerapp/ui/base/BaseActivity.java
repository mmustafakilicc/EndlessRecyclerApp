package com.mklc.endlessrecyclerapp.ui.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mklc.endlessrecyclerapp.ERApplication;
import com.mklc.endlessrecyclerapp.R;
import com.mklc.endlessrecyclerapp.di.module.viewmodel.ViewModelFactory;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {

    @Inject
    public ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((ERApplication) getApplicationContext()).applicationComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}