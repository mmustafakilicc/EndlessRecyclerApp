package com.mklc.endlessrecyclerapp.ui.adapter

data class AdapterViewData(
    val item: Any? = null,
    val id: Int = -1,
    val type: Type = Type.LOADER
)

enum class Type(val value: Int){
    LOADER(0),
    ITEM(1)
}