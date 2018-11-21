package com.blake.goodscenter.presenter.view

import com.blake.baselibrary.presenter.view.BaseView
import com.blake.goodscenter.data.protocol.Goods

/**
 * Create by Pidan
 */
interface GoodsListView : BaseView {
    fun onGetGoodsListResult(result: MutableList<Goods>?)
}