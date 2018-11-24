package com.blake.goodscenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blake.baselibrary.ext.loadUrl
import com.blake.baselibrary.ext.onClick
import com.blake.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.blake.baselibrary.widgets.DefaultTextWatcher
import com.blake.goodscenter.R
import com.blake.goodscenter.data.protocol.CartGoods
import com.blake.goodscenter.event.CartAllCheckedEvent
import com.blake.goodscenter.event.UpdateTotalPriceEvent
import com.blake.goodscenter.getEditText
import com.eightbitlab.rxbus.Bus
import com.kotlin.base.utils.YuanFenConverter
import kotlinx.android.synthetic.main.layout_cart_goods_item.view.*

/*
    购物车数据适配器
 */
class CartGoodsAdapter(context: Context) : BaseRecyclerViewAdapter<CartGoods, CartGoodsAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_cart_goods_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        //是否选中
        holder.itemView.mCheckedCb.isChecked = model.isSelected
        //加载商品图片
        holder.itemView.mGoodsIconIv.loadUrl(model.goodsIcon)
        //商品描述
        holder.itemView.mGoodsDescTv.text = model.goodsDesc
        //商品SKU
        holder.itemView.mGoodsSkuTv.text = model.goodsSku
        //商品价格
        holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(model.goodsPrice)
        //商品数量
        holder.itemView.mGoodsCountBtn.setCurrentNumber(model.goodsCount)
        holder.itemView.mGoodsCountBtn.buyMax = 99
        //选中按钮事件
        holder.itemView.mCheckedCb.onClick {
            model.isSelected = holder.itemView.mCheckedCb.isChecked
            val isAllChecked = dataList.all { it.isSelected }
            Bus.send(CartAllCheckedEvent(isAllChecked))
        }

        holder.itemView.mGoodsCountBtn.getEditText().addTextChangedListener(object : DefaultTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                model.goodsCount = if (s.toString().isEmpty())
                    1
                else if (s.toString().toInt() > holder.itemView.mGoodsCountBtn.buyMax)
                    holder.itemView.mGoodsCountBtn.buyMax
                else
                    s.toString().toInt()

                Bus.send(UpdateTotalPriceEvent())
            }
        })
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
