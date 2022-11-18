package com.mklc.endlessrecyclerapp.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("show")
fun View.show(visible: Boolean){
    visibility = if(visible){
        View.VISIBLE
    }else{
        View.GONE
    }
}