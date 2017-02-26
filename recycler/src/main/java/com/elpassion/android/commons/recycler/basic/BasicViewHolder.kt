package com.elpassion.android.commons.recycler.basic

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View


open class BasicViewHolder<in Item>(itemView: View) : ViewHolder(itemView){
    open fun bind(item: Item) {}
}