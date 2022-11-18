package com.mklc.endlessrecyclerapp.ui.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractEndlessListAdapter<T : Any> : ListAdapter<T, RecyclerView.ViewHolder>(BaseItemCallback<T>()) {

    companion object{
        const val PAGE_SIZE = 10
    }

    private var currentPage = 1
    private var scrollEnabled = true

    fun getCurrentPage() = currentPage

    fun isScrollEnabled() = scrollEnabled

    open fun stop(){
        scrollEnabled = false
    }

    override fun submitList(list: MutableList<T>?) {
        currentPage++
        super.submitList(list)
    }

    class BaseItemCallback<T : Any> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.toString() == newItem.toString()

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    }

}