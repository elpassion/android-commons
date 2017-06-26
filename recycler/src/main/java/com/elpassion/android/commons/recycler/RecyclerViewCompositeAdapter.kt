package com.elpassion.android.commons.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.elpassion.android.commons.recycler.components.ItemsStrategy
import com.elpassion.android.commons.recycler.components.base.ItemAdapter

@Deprecated("Use BasicAdapter", ReplaceWith("BasicAdapter"))
class RecyclerViewCompositeAdapter<T : ItemAdapter<out RecyclerView.ViewHolder>>(
        private val itemsStrategy: ItemsStrategy<T>,
        private val getItemIdentifier: (position: Int) -> Long = { 0L },
        init: (RecyclerViewCompositeAdapter<T>.() -> Unit) = {}) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        init()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return allItems().first { it.viewType == viewType }.onCreateViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        allItems()[position].onBindBaseViewHolder(holder)
    }

    override fun getItemCount() = allItems().size

    override fun getItemViewType(position: Int) = allItems()[position].viewType

    override fun getItemId(position: Int) = getItemIdentifier(position)

    private fun allItems() = itemsStrategy.allItems()
}
