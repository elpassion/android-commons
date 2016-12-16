package com.elpassion.android.commons.recycler.components.stable

import android.support.annotation.LayoutRes
import com.elpassion.android.commons.recycler.components.base.ItemAdapter

abstract class StableItemAdapter(val stableId: Long, @LayoutRes viewType: Int) : ItemAdapter(viewType)