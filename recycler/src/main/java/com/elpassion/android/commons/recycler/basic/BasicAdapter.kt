package com.elpassion.android.commons.recycler.basic

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.NO_ID

abstract class BasicAdapter<Item>(
        var items: List<Item>
) : RecyclerView.Adapter<ViewHolderBinder<Item>>() {

    override fun onBindViewHolder(holder: ViewHolderBinder<Item>, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    override fun getItemId(position: Int) = (items[position] as? WithStableId)?.id ?: NO_ID
}