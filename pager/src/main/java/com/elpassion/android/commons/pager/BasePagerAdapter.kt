package com.elpassion.android.commons.pager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

abstract class BasePagerAdapter(val items: List<PagerItemAdapter>) : PagerAdapter() {

    override fun getCount() = items.size

    override fun isViewFromObject(view: View, item: Any) = view == item

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = items[position]
        val view = inflate(container, item)
        item.bind(view)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    private fun inflate(container: ViewGroup, item: PagerItemAdapter): View {
        return LayoutInflater.from(container.context).inflate(item.layout, container, false)
    }
}