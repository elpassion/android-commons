@file:JvmName("Adapters")

package com.elpassion.android.commons.recycler.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.elpassion.android.commons.recycler.RecyclerViewCompositeAdapter
import com.elpassion.android.commons.recycler.basic.BasicAdapter
import com.elpassion.android.commons.recycler.basic.BasicList
import com.elpassion.android.commons.recycler.basic.BasicViewHolder
import com.elpassion.android.commons.recycler.components.ItemsStrategy
import com.elpassion.android.commons.recycler.components.base.ItemAdapter
import com.elpassion.android.commons.recycler.components.base.ListItemsStrategy
import com.elpassion.android.commons.recycler.components.base.MutableListItemsStrategy
import com.elpassion.android.commons.recycler.components.group.SectionedItemsStrategy
import com.elpassion.android.commons.recycler.components.stable.StableItemAdapter
import com.elpassion.android.commons.recycler.components.stable.createStableIdInitialization
import com.elpassion.android.commons.recycler.components.stable.getStableItemIdentifier
import com.elpassion.android.view.inflate
import java.util.*

fun recyclerViewAdapter(adapters: List<ItemAdapter<*>>) = RecyclerViewCompositeAdapter(ListItemsStrategy(adapters))

fun mutableRecyclerViewAdapter(adapters: MutableList<ItemAdapter<*>> = mutableListOf()) = RecyclerViewCompositeAdapter(MutableListItemsStrategy(adapters))

fun stableRecyclerViewAdapter(itemsStrategy: ItemsStrategy<StableItemAdapter<out RecyclerView.ViewHolder>>) =
        RecyclerViewCompositeAdapter(
                itemsStrategy = itemsStrategy,
                getItemIdentifier = getStableItemIdentifier(itemsStrategy),
                init = createStableIdInitialization())

fun <Section, Item : StableItemAdapter<out RecyclerView.ViewHolder>> stableSectionedRecyclerViewAdapter(itemsStrategy: SectionedItemsStrategy<Section, Item>) =
        RecyclerViewCompositeAdapter(
                itemsStrategy = itemsStrategy,
                getItemIdentifier = getStableItemIdentifier(itemsStrategy),
                init = createStableIdInitialization())

fun <Item> basicAdapterWithHolder(items: BasicList<Item>, createHolder: (parent: ViewGroup) -> BasicViewHolder<Item>) =
        object : BasicAdapter<Item>(items) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = createHolder(parent)
        }

fun <Item> basicAdapterWithLayoutAndBinder(items: BasicList<Item>, layout: Int, binder: (holder: BasicViewHolder<Item>, item: Item) -> Unit) =
        basicAdapterWithHolder(items) { parent ->
            object : BasicViewHolder<Item>(parent.inflate(layout)) {
                override fun bind(item: Item) = binder(this, item)
            }
        }

fun <Item> basicAdapterWithCreator(items: BasicList<Item>, getTypeAndCreator: (position: Int) -> Pair<Int, (parent: ViewGroup) -> BasicViewHolder<Item>>) =
        object : BasicAdapter<Item>(items) {
            private val creators = HashMap<Int, (parent: ViewGroup) -> BasicViewHolder<Item>>()
            override fun getItemViewType(position: Int): Int {
                val (type, creator) = getTypeAndCreator(position)
                creators[type] = creator
                return type
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = creators[viewType]!!(parent)
        }

fun <Item> basicAdapterWithConstructors(items: BasicList<Item>, getLayoutAndConstructor: (position: Int) -> Pair<Int, (itemView: View) -> BasicViewHolder<Item>>) =
        basicAdapterWithCreator(items) { position ->
            val (layout, constructor) = getLayoutAndConstructor(position)
            layout to { parent: ViewGroup -> constructor(parent.inflate(layout)) }
        }
