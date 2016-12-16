package com.elpassion.android.commons.recycler_example.common

import android.support.v7.widget.RecyclerView.ViewHolder
import com.elpassion.android.commons.recycler.components.base.ItemAdapter
import com.elpassion.android.commons.recycler_example.R
import kotlinx.android.synthetic.main.github_item.view.*

class OtherSimpleUserItemAdapter(private val user: User) : ItemAdapter(R.layout.other_github_item) {

    override fun onBindViewHolder(holder: ViewHolder) {
        with(holder.itemView) {
            userName.text = user.userName
            organization.text = user.organization
        }
    }
}