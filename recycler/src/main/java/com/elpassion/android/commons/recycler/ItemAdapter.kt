package com.elpassion.android.commons.recycler

import android.support.v7.widget.RecyclerView
import android.view.View

interface ItemAdapter<VH : RecyclerView.ViewHolder> {

    val viewType: Int

    fun onCreateViewHolder(itemView: View): VH
    fun onBindViewHolder(holder: VH)

    fun onBindBaseViewHolder(holder: RecyclerView.ViewHolder) {
        @Suppress("UNCHECKED_CAST")
        onBindViewHolder(holder as VH)
    }
}