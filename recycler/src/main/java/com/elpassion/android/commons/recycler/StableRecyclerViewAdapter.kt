package com.elpassion.android.commons.recycler

open class StableRecyclerViewAdapter : GenericRecyclerViewAdapter<StableItemAdapter<*>>(){

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = adapters[position].stableId
}