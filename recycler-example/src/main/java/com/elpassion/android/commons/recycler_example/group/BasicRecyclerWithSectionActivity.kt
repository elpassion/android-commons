package com.elpassion.android.commons.recycler_example.group

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.elpassion.android.commons.recycler.adapters.basicAdapterWithLayoutAndBinder
import com.elpassion.android.commons.recycler.basic.asBasicListWithSections
import com.elpassion.android.commons.recycler_example.R
import com.elpassion.android.commons.recycler_example.common.User
import com.elpassion.android.commons.recycler_example.common.createManyUsers
import kotlinx.android.synthetic.main.github_item.view.*
import kotlinx.android.synthetic.main.recycler_view.*

class BasicRecyclerWithSectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val users = createManyUsers().groupBy(User::organization).asBasicListWithSections()
        recyclerView.adapter = basicAdapterWithLayoutAndBinder(users, R.layout.github_item) { holder, user ->
            with(holder.itemView) {
                userName.text = user.name
                organization.text = user.organization
            }
        }

        Log.i(BasicRecyclerWithSectionActivity::class.java.name, "Section A size: ${users.sections["A"]!!.size}")
        Log.i(BasicRecyclerWithSectionActivity::class.java.name, "Section B size: ${users.sections["B"]!!.size}")
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, BasicRecyclerWithSectionActivity::class.java))
        }

        const val DESCRIPTION = "Basic recycler with sections"
    }
}