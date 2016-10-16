package com.elpassion.android.commons.recycler

import android.support.v7.widget.RecyclerView
import android.view.View

class StableItemAdapter<T, VH : RecyclerView.ViewHolder>(viewType: Int,
                                                         creator: (View) -> VH,
                                                         item: T,
                                                         val stableId: Long,
                                                         binder: T.(VH) -> Unit) :
        ItemAdapter<VH> by ItemAdapterImpl(viewType, creator, item, binder)
