package com.elpassion.android.commons.recycler.components.base

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView.ViewHolder

abstract class ItemAdapter(@LayoutRes val viewType: Int) {
    abstract fun onBindViewHolder(holder: ViewHolder)
}
