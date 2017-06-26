package com.elpassion.android.commons.recycler.components.stable

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import com.elpassion.android.commons.recycler.components.base.ItemAdapter

@Deprecated("Use BasicAdapter instead", ReplaceWith("BasicAdapter"))
abstract class StableItemAdapter<VH : RecyclerView.ViewHolder>(
        val stableId: Long,
        @LayoutRes layoutId: Int
) : ItemAdapter<VH>(layoutId)