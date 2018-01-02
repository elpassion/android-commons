package com.elpassion.android.commons.recycler_example.common

import android.view.View
import com.elpassion.android.commons.recycler.basic.ViewHolderBinder
import kotlinx.android.synthetic.main.github_item.view.*

// tag::recycler-simple-user-view-holder[]
class SimpleUserViewHolder(itemView: View) : ViewHolderBinder<User>(itemView) {

    override fun bind(item: User) {
        itemView.userName.text = item.name
        itemView.organization.text = item.organization
    }
}
// end::recycler-simple-user-view-holder[]