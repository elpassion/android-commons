package com.elpassion.android.commons.recycler_example.common

import android.view.View
import com.elpassion.android.commons.recycler.basic.BasicViewHolder
import kotlinx.android.synthetic.main.other_github_item.view.*

class OtherSimpleUserViewHolder(itemView: View) : BasicViewHolder<User>(itemView) {
    override fun bind(item: User) {
        itemView.userName.text = item.name.reversed()
        itemView.organization.text = item.organization
    }
}