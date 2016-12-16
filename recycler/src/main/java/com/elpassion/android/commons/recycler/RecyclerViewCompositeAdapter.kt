package com.elpassion.android.commons.recycler

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.ViewGroup
import com.elpassion.android.commons.recycler.components.ItemsStrategy
import com.elpassion.android.commons.recycler.components.base.ItemAdapter
import com.elpassion.android.view.inflate

class RecyclerViewCompositeAdapter<T : ItemAdapter>(
        private val itemsStrategy: ItemsStrategy<T>,
        private val getItemIdentifier: (position: Int) -> Long = { 0L },
        init: (RecyclerViewCompositeAdapter<T>.() -> Unit) = {}) : RecyclerView.Adapter<ViewHolder>() {

    init {
        init()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = object : ViewHolder(parent.inflate(viewType)) {}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        allItems()[position].onBindViewHolder(holder)
    }

    override fun getItemCount() = allItems().size

    override fun getItemViewType(position: Int) = allItems()[position].viewType

    override fun getItemId(position: Int) = getItemIdentifier(position)

    private fun allItems() = itemsStrategy.allItems()
}
