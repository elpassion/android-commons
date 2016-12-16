package com.elpassion.android.commons.recycler_example.common

import android.support.v7.widget.RecyclerView.ViewHolder
import com.elpassion.android.commons.recycler.components.stable.StableItemAdapter
import com.elpassion.android.commons.recycler_example.R
import kotlinx.android.synthetic.main.github_item.view.*

class StableUserItemAdapter(private val user: User) : StableItemAdapter(user.id, R.layout.github_item) {
    override fun onBindViewHolder(holder: ViewHolder) {
        with(holder.itemView) {
            userName.text = user.userName
            organization.text = user.organization
        }
    }
}