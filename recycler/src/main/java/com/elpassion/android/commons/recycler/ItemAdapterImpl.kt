package com.elpassion.android.commons.recycler

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View

class ItemAdapterImpl<T, VH : RecyclerView.ViewHolder>(@LayoutRes override val viewType: Int,
                                                       val createHolder: (View) -> VH,
                                                       val item: T,
                                                       val bind: T.(VH) -> Unit) : ItemAdapter<VH> {

    override fun onCreateViewHolder(itemView: View) = createHolder(itemView)
    override fun onBindViewHolder(holder: VH) = item.bind(holder)
}