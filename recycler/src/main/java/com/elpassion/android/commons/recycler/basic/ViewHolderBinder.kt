package com.elpassion.android.commons.recycler.basic

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class ViewHolderBinder<in Item>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    open fun bind(item: Item) {}
}