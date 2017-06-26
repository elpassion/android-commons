@file:JvmName("Adapters")

package com.elpassion.android.commons.recycler.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.elpassion.android.commons.recycler.RecyclerViewCompositeAdapter
import com.elpassion.android.commons.recycler.basic.BasicAdapter
import com.elpassion.android.commons.recycler.basic.ViewHolderBinder
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

@Deprecated("Use BasicAdapter instead", ReplaceWith("BasicAdapter"))
fun recyclerViewAdapter(
        adapters: List<ItemAdapter<*>>
) = RecyclerViewCompositeAdapter(ListItemsStrategy(adapters))

@Deprecated("Use BasicAdapter with MutableList instead", ReplaceWith("BasicAdapter + MutableList"))
fun mutableRecyclerViewAdapter(
        adapters: MutableList<ItemAdapter<*>> = mutableListOf()
) = RecyclerViewCompositeAdapter(MutableListItemsStrategy(adapters))

@Deprecated("Use BasicAdapter with WithStableId instead", ReplaceWith("BasicAdapter + WithStableId"))
fun stableRecyclerViewAdapter(
        itemsStrategy: ItemsStrategy<StableItemAdapter<out RecyclerView.ViewHolder>>
) = RecyclerViewCompositeAdapter(
        itemsStrategy = itemsStrategy,
        getItemIdentifier = getStableItemIdentifier(itemsStrategy),
        init = createStableIdInitialization()
)

@Deprecated("Use BasicAdapter with BasicListWithSections instead", ReplaceWith("BasicAdapter + BasicListWithSections"))
fun <Section, Item : StableItemAdapter<out RecyclerView.ViewHolder>> stableSectionedRecyclerViewAdapter(
        itemsStrategy: SectionedItemsStrategy<Section, Item>
) = RecyclerViewCompositeAdapter(
        itemsStrategy = itemsStrategy,
        getItemIdentifier = getStableItemIdentifier(itemsStrategy),
        init = createStableIdInitialization()
)

fun <Item> basicAdapterWithHolder(items: List<Item>, createHolder: (parent: ViewGroup) -> ViewHolderBinder<Item>) =
        object : BasicAdapter<Item>(items) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = createHolder(parent)
        }

fun <Item> basicAdapterWithLayoutAndBinder(
        items: List<Item>,
        layout: Int,
        binder: (holder: ViewHolderBinder<Item>, item: Item) -> Unit
) = basicAdapterWithHolder(items) { parent ->
    object : ViewHolderBinder<Item>(parent.inflate(layout)) {

        override fun bind(item: Item) = binder(this, item)
    }
}

fun <Item> basicAdapterWithCreator(
        items: List<Item>,
        getTypeAndCreator: (position: Int) -> Pair<Int, (parent: ViewGroup) -> ViewHolderBinder<Item>>
) = object : BasicAdapter<Item>(items) {

    private val creators = HashMap<Int, (parent: ViewGroup) -> ViewHolderBinder<Item>>()

    override fun getItemViewType(position: Int): Int {
        val (type, creator) = getTypeAndCreator(position)
        creators[type] = creator
        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = creators[viewType]!!(parent)
}

fun <Item> basicAdapterWithConstructors(
        items: List<Item>,
        getLayoutAndConstructor: (position: Int) -> Pair<Int, (itemView: View) -> ViewHolderBinder<Item>>
) = basicAdapterWithCreator(items) { position ->
    val (layout, constructor) = getLayoutAndConstructor(position)
    layout to { parent: ViewGroup -> constructor(parent.inflate(layout)) }
}
