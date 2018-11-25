package com.kotlin.order.presenter

import com.blake.baselibrary.ext.execute
import com.blake.baselibrary.presenter.BasePresenter
import com.blake.baselibrary.rx.BaseSubscriber
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.presenter.view.OrderConfirmView
import com.kotlin.order.service.OrderService
import javax.inject.Inject

/**
 * Create by Pidan
 */
class OrderConfirmPresenter @Inject constructor() : BasePresenter<OrderConfirmView>() {
    @Inject
    lateinit var service: OrderService

    fun getOrderById(orderId: Int) {
        if (!checkNetwork()) {
            return
        }
        mView.showLoading()
        service.getOrderById(orderId)
            .execute(lifecycleProvider, object : BaseSubscriber<Order>(mView) {
                override fun onNext(t: Order) {
                    mView.onGetOrderByIdResult(t)
                }
            })
    }
}