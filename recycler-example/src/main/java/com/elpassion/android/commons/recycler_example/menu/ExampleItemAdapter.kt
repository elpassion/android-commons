package com.elpassion.android.commons.recycler_example.menu

import android.support.v7.widget.RecyclerView.ViewHolder
import com.elpassion.android.commons.recycler.components.base.ItemAdapter
import com.elpassion.android.commons.recycler_example.R
import kotlinx.android.synthetic.main.example_item.view.*

class ExampleItemAdapter(private val exampleItem: ExampleItem) : ItemAdapter(R.layout.example_item) {

    override fun onBindViewHolder(holder: ViewHolder) {
        holder.itemView.example_name.text = exampleItem.name
        holder.itemView.setOnClickListener { exampleItem.onClick() }
    }
}