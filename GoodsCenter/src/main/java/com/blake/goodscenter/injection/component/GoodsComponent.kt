package com.blake.goodscenter.injection.component

import com.blake.baselibrary.injection.PerComponentScope
import com.blake.baselibrary.injection.component.ActivityComponent
import com.blake.goodscenter.injection.module.CategoryModule
import com.blake.goodscenter.injection.module.GoodsModule
import com.blake.goodscenter.ui.activity.GoodsActivity
import com.blake.goodscenter.ui.fragment.CategoryFragment
import com.blake.goodscenter.ui.fragment.GoodsDetailTabOneFragment
import com.blake.goodscenter.ui.fragment.GoodsDetailTabTwoFragment
import dagger.Component

/**
 * Create by Pidan
 */
@PerComponentScope
@Component(modules = [GoodsModule::class], dependencies = [ActivityComponent::class])
interface GoodsComponent {
    fun inject(activity: GoodsActivity)
    fun inject(fragment: GoodsDetailTabOneFragment)
    fun inject(fragment: GoodsDetailTabTwoFragment)
}