package com.mklc.endlessrecyclerapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mklc.endlessrecyclerapp.R
import com.mklc.endlessrecyclerapp.data.UserDto
import com.mklc.endlessrecyclerapp.databinding.ItemLoaderBinding
import com.mklc.endlessrecyclerapp.databinding.ItemUserBinding

class EndlessRecyclerViewAdapter : AbstractEndlessListAdapter<AdapterViewData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if(viewType == Type.LOADER.value){
            LoaderVH(DataBindingUtil.inflate(inflater, R.layout.item_loader, parent, false))
        }else{
            UserVH(DataBindingUtil.inflate(inflater, R.layout.item_user, parent, false))
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if(item.type == Type.ITEM){
            (holder as UserVH).binding.user = item.item as UserDto
            holder.binding.index = (position + 1).toString()
        }
    }

    override fun submitList(list: MutableList<AdapterViewData>?) {
        if(!list.isNullOrEmpty() && list.size > currentList.size){
            if(list.size == PAGE_SIZE * getCurrentPage()){
                list.add(AdapterViewData(type = Type.LOADER))
            }
        }
        super.submitList(list)
    }

    override fun stop() {
        super.stop()
        if(currentList[currentList.lastIndex].type == Type.LOADER){
            val list = currentList.toMutableList()
            list.removeLast()
            super.submitList(list)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type.value
    }

    class UserVH(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
    class LoaderVH(binding: ItemLoaderBinding): RecyclerView.ViewHolder(binding.root)

    companion object {
        private val DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<AdapterViewData>() {
            override fun areItemsTheSame(oldItem: AdapterViewData, newItem: AdapterViewData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: AdapterViewData, newItem: AdapterViewData): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}