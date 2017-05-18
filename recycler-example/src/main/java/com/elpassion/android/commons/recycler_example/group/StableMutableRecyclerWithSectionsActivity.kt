package com.elpassion.android.commons.recycler_example.group

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.elpassion.android.commons.recycler.RecyclerViewCompositeAdapter
import com.elpassion.android.commons.recycler.adapters.stableSectionedRecyclerViewAdapter
import com.elpassion.android.commons.recycler.components.group.impl.CachedMapItemsStrategy
import com.elpassion.android.commons.recycler.components.group.impl.MutableMapItemsStrategy
import com.elpassion.android.commons.recycler_example.R
import com.elpassion.android.commons.recycler_example.common.StableUserItemAdapter
import com.elpassion.android.commons.recycler_example.common.User
import com.elpassion.android.commons.recycler_example.common.createManyUsers
import com.elpassion.android.commons.recycler_example.common.createUsersWithASection
import kotlinx.android.synthetic.main.recycler_view_with_action.*

class StableMutableRecyclerWithSectionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_with_action)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val users = createManyUsers()
        val adapters = getAdapters(users)
        val itemsStrategy = CachedMapItemsStrategy(MutableMapItemsStrategy(adapters))
        val adapterCompositor = stableSectionedRecyclerViewAdapter(itemsStrategy)
        recyclerView.adapter = adapterCompositor

        clearSectionButton.setOnClickListener {
            onClearClicked(adapterCompositor, itemsStrategy)
        }
        restoreSectionButton.setOnClickListener {
            orRestoreClicked(adapterCompositor, itemsStrategy)
        }
    }

    private fun getAdapters(users: List<User>) = users
            .groupBy(User::organization)
            .mapValues { it.value.map(::StableUserItemAdapter) }

    private fun onClearClicked(adapterCompositor: RecyclerViewCompositeAdapter<StableUserItemAdapter>, itemsStrategy: CachedMapItemsStrategy<String, StableUserItemAdapter>) {
        itemsStrategy.set("A", emptyList())
        adapterCompositor.notifyDataSetChanged()
        restoreSectionButton.isEnabled = true
        clearSectionButton.isEnabled = false
    }

    private fun orRestoreClicked(adapterCompositor: RecyclerViewCompositeAdapter<StableUserItemAdapter>, itemsStrategy: CachedMapItemsStrategy<String, StableUserItemAdapter>) {
        itemsStrategy.addAll("A", createUsersWithASection().map(::StableUserItemAdapter))
        adapterCompositor.notifyDataSetChanged()
        restoreSectionButton.isEnabled = false
        clearSectionButton.isEnabled = true
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, StableMutableRecyclerWithSectionsActivity::class.java))
        }

        const val DESCRIPTION = "Stable mutable recycler with sections"
    }
}