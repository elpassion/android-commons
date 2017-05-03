package com.elpassion.android.commons.recycler_example.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.elpassion.android.commons.recycler.adapters.recyclerViewAdapter
import com.elpassion.android.commons.recycler_example.R
import com.elpassion.android.commons.recycler_example.common.OtherSimpleUserItemAdapter
import com.elpassion.android.commons.recycler_example.common.SimpleUserItemAdapter
import com.elpassion.android.commons.recycler_example.common.User
import com.elpassion.android.commons.recycler_example.common.createManyUsers
import kotlinx.android.synthetic.main.recycler_view.*

class SimpleListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view)
        val users = createManyUsers()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter(users.map { getItemAdapter(it) })
    }

    private fun getItemAdapter(user: User) = when (user.organization) {
        "A" -> SimpleUserItemAdapter(user)
        else -> OtherSimpleUserItemAdapter(user)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SimpleListActivity::class.java))
        }

        const val DESCRIPTION = "Simple recycler with different items views"
    }
}
