package com.blake.goodscenter.presenter.view

import com.blake.baselibrary.presenter.view.BaseView
import com.blake.goodscenter.data.protocol.CartGoods
import com.blake.goodscenter.data.protocol.Category

/**
 * Create by Pidan
 */
interface CartListView : BaseView {
    fun onGetCartListResult(result: MutableList<CartGoods>?)

    fun onDeleteCartListResult(result: Boolean)

    fun onSubmitCartResult(result: Int)
}