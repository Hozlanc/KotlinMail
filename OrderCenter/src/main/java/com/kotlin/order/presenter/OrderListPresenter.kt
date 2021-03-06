package com.kotlin.order.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.presenter.view.OrderListView
import com.kotlin.order.service.OrderService
import javax.inject.Inject

/*
    订单列表Presenter
 */
class OrderListPresenter @Inject constructor() : BasePresenter<OrderListView>() {

    @Inject
    lateinit var orderService: OrderService

    /*
        根据订单状态获取订单列表
     */
    fun getOrderList(orderStatus: Int) {
        if (!checkNetwork()) {
            return
        }
        mView.showLoading()
        orderService.getOrderList(orderStatus)
            .execute(lifecycleProvider, object : BaseSubscriber<MutableList<Order>?>(mView) {
                override fun onNext(t: MutableList<Order>?) {
                    mView.onGetOrderListResult(t)
                }
            })

    }

    /*
        订单确认收货
     */
    fun confirmOrder(orderId: Int) {
        if (!checkNetwork()) {
            return
        }
        mView.showLoading()
        orderService.confirmOrder(orderId).execute(lifecycleProvider, object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onConfirmOrderResult(t)
            }
        })

    }

    /*
        取消订单
     */
    fun cancelOrder(orderId: Int) {
        if (!checkNetwork()) {
            return
        }
        mView.showLoading()
        orderService.cancelOrder(orderId).execute(lifecycleProvider, object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onCancelOrderResult(t)
            }
        })

    }


}
