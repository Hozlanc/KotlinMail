package com.kotlin.order.presenter.view

import com.blake.baselibrary.presenter.view.BaseView
import com.kotlin.order.data.protocol.Order

/**
 * Create by Pidan
 */
interface OrderConfirmView : BaseView {
    fun onGetOrderByIdResult(result: Order)
}