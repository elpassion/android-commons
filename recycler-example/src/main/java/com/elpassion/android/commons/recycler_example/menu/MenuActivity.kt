package com.elpassion.android.commons.recycler_example.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.elpassion.android.commons.recycler.adapters.basicAdapterWithLayoutAndBinder
import com.elpassion.android.commons.recycler_example.R
import com.elpassion.android.commons.recycler_example.group.BasicContactsListActivity
import com.elpassion.android.commons.recycler_example.group.BasicMutableRecyclerWithSectionsActivity
import com.elpassion.android.commons.recycler_example.group.BasicRecyclerWithSectionActivity
import com.elpassion.android.commons.recycler_example.list.BasicListActivity
import kotlinx.android.synthetic.main.example_item.view.*
import kotlinx.android.synthetic.main.menu_layout.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_layout)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // tag::recycler-basic-adapter-with-layout-and-binder[]
        val examples = listOf(
                ExampleItem(name = BasicListActivity.DESCRIPTION,
                        onClick = { BasicListActivity.start(this) }),
                ExampleItem(name = BasicRecyclerWithSectionActivity.DESCRIPTION,
                        onClick = { BasicRecyclerWithSectionActivity.start(this) }),
                ExampleItem(name = BasicMutableRecyclerWithSectionsActivity.DESCRIPTION,
                        onClick = { BasicMutableRecyclerWithSectionsActivity.start(this) }),
                ExampleItem(name = BasicContactsListActivity.DESCRIPTION,
                        onClick = { BasicContactsListActivity.start(this) })
        )

        recyclerView.adapter = basicAdapterWithLayoutAndBinder(examples, R.layout.example_item) { holder, item ->
            holder.itemView.example_name.text = item.name
            holder.itemView.setOnClickListener { item.onClick() }
        }
        // end::recycler-basic-adapter-with-layout-and-binder[]
    }
}