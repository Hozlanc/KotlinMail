package com.kotlin.order.injection.component

import com.blake.baselibrary.injection.PerComponentScope
import com.blake.baselibrary.injection.component.ActivityComponent
import com.kotlin.order.injection.module.OrderModule
import com.kotlin.order.ui.activity.OrderConfirmActivity
import dagger.Component

/**
 * Create by Pidan
 */
@PerComponentScope
@Component(modules = [OrderModule::class], dependencies = [ActivityComponent::class])
interface OrderComponent {
    fun inject(activity: OrderConfirmActivity)
}