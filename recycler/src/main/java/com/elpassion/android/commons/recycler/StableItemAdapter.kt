package com.elpassion.android.commons.recycler

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView

abstract class StableItemAdapter<VH : RecyclerView.ViewHolder>(val stableId: Long,
                                                               @LayoutRes layoutId: Int) : ItemAdapter<VH>(layoutId)