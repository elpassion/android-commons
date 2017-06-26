package com.elpassion.android.commons.recycler_example.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.elpassion.android.commons.recycler.adapters.basicAdapterWithConstructors
import com.elpassion.android.commons.recycler_example.R
import com.elpassion.android.commons.recycler_example.common.OtherSimpleUserViewHolder
import com.elpassion.android.commons.recycler_example.common.SimpleUserViewHolder
import com.elpassion.android.commons.recycler_example.common.User
import com.elpassion.android.commons.recycler_example.common.createManyUsers
import kotlinx.android.synthetic.main.recycler_view.*

class BasicListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view)
        val users = createManyUsers()
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = basicAdapterWithConstructors(users) { position ->
            getLayoutAndConstructor(users[position])
        }
    }

    private fun getLayoutAndConstructor(user: User) = when (user.organization) {
        "A" -> R.layout.github_item to ::SimpleUserViewHolder
        else -> R.layout.other_github_item to ::OtherSimpleUserViewHolder
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, BasicListActivity::class.java))
        }

        const val DESCRIPTION = "Basic recycler with different item views"
    }
}
