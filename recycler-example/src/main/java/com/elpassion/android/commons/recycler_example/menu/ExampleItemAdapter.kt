package com.elpassion.android.commons.recycler_example.menu

import android.support.v7.widget.RecyclerView
import android.view.View
import com.elpassion.android.commons.recycler.components.base.ItemAdapter
import com.elpassion.android.commons.recycler_example.R
import kotlinx.android.synthetic.main.example_item.view.*

class ExampleItemAdapter(
        private val exampleItem: ExampleItem
) : ItemAdapter<ExampleItemAdapter.VH>(R.layout.example_item) {

    override fun onCreateViewHolder(itemView: View) = VH(itemView)

    override fun onBindViewHolder(holder: VH) {
        holder.itemView.example_name.text = exampleItem.name
        holder.itemView.setOnClickListener { exampleItem.onClick() }
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)
}