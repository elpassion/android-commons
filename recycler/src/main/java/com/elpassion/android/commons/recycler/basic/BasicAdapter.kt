package com.elpassion.android.commons.recycler.basic

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_ID


abstract class BasicAdapter<Item>(
        var items: List<Item>
) : RecyclerView.Adapter<ViewHolderBinder<Item>>() {

    override fun onBindViewHolder(holder: ViewHolderBinder<Item>, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    override fun getItemId(position: Int) = (items[position] as? WithStableId)?.id ?: NO_ID
}