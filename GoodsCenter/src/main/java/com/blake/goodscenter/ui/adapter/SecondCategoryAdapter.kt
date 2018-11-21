package com.blake.goodscenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blake.baselibrary.ext.loadUrl
import com.blake.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.blake.goodscenter.R
import com.blake.goodscenter.data.protocol.Category
import kotlinx.android.synthetic.main.layout_second_category_item.view.*

/**
 * Create by Pidan
 */
class SecondCategoryAdapter(context: Context) :
    BaseRecyclerViewAdapter<Category, SecondCategoryAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_second_category_item, viewGroup, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        holder.itemView.mCategoryIconIv.loadUrl(model.categoryIcon)
        holder.itemView.mSecondCategoryNameTv.text = model.categoryName
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}