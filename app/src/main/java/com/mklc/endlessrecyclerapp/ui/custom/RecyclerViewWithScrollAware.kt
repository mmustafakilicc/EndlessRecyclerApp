package com.mklc.endlessrecyclerapp.ui.custom

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mklc.endlessrecyclerapp.ui.adapter.AbstractEndlessListAdapter

typealias ScrollEndedCallBack = ((Int) -> Unit)

class RecyclerViewWithScrollAware: RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, @Nullable attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    private var scrollEndedCallBack: ScrollEndedCallBack? = null

    private val scrollListener: OnScrollListener = object : OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if(adapter != null
                && adapter is AbstractEndlessListAdapter<*>
                && layoutManager != null
                && layoutManager is LinearLayoutManager){
                if((adapter as AbstractEndlessListAdapter<*>).isScrollEnabled()){
                    if((layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() >=
                        (adapter as AbstractEndlessListAdapter<*>).itemCount - 1){
                        scrollEndedCallBack?.invoke((adapter as AbstractEndlessListAdapter<*>).getCurrentPage())
                    }
                }
            }
        }
    }

    init {
        addOnScrollListener(scrollListener)
    }

    fun listenScrollCallBack(scrollEndedCallBack: ScrollEndedCallBack){
        this.scrollEndedCallBack = scrollEndedCallBack
    }
}