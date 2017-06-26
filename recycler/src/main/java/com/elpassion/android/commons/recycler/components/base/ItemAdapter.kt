package com.elpassion.android.commons.recycler.components.base

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View

@Deprecated("Use BasicAdapter instead", ReplaceWith("BasicAdapter"))
abstract class ItemAdapter<VH : RecyclerView.ViewHolder>(@LayoutRes val viewType: Int) {

    abstract fun onCreateViewHolder(itemView: View): VH
    abstract fun onBindViewHolder(holder: VH)

    fun onBindBaseViewHolder(holder: RecyclerView.ViewHolder) {
        @Suppress("UNCHECKED_CAST")
        onBindViewHolder(holder as VH)
    }
}
