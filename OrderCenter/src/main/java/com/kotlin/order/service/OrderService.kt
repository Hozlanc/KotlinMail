package com.kotlin.order.service

import com.blake.baselibrary.data.protocol.BaseResp
import com.kotlin.order.data.protocol.Order
import rx.Observable

/**
 * Create by Pidan
 */
interface OrderService {
    fun getOrderById(orderId: Int): Observable<Order>

    fun submitOrder(order: Order): Observable<String>
}