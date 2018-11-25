package com.kotlin.order.service.impl

import com.blake.baselibrary.ext.convert
import com.kotlin.order.service.OrderService
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.data.repository.OrderRepository
import rx.Observable
import javax.inject.Inject

/**
 * Create by Pidan
 */
class OrderServiceImpl @Inject constructor() : OrderService {
    @Inject
    lateinit var repository: OrderRepository

    override fun submitOrder(order: Order): Observable<String> =
        repository.submitOrder(order).convert()

    override fun getOrderById(orderId: Int): Observable<Order> =
        repository.getOrderById(orderId).convert()

}
