package com.elpassion.android.commons.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import java.util.*

open class GenericRecyclerViewAdapter<T : ItemAdapter<out RecyclerView.ViewHolder>> : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected val adapters: MutableList<T>

    constructor() {
        adapters = ArrayList()
    }

    constructor(immutableList: List<T>) {
        adapters = immutableList.toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val adapter = adapters.first { it.viewType == viewType }
        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return adapter.onCreateViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        adapters[position].onBindBaseViewHolder(holder)
    }

    override fun getItemCount() = adapters.size

    override fun getItemViewType(position: Int) = adapters[position].viewType
}