package com.elpassion.android.commons.recycler_example.common

import android.support.v7.widget.RecyclerView
import android.view.View
import com.elpassion.android.commons.recycler.components.stable.StableItemAdapter
import com.elpassion.android.commons.recycler_example.R
import kotlinx.android.synthetic.main.github_item.view.*

class StableUserItemAdapter(
        private val user: User
) : StableItemAdapter<StableUserItemAdapter.VH>(user.id, R.layout.github_item) {

    override fun onCreateViewHolder(itemView: View) = VH(itemView)

    override fun onBindViewHolder(holder: VH) {
        with(holder.itemView) {
            userName.text = user.name
            organization.text = user.organization
        }
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)
}