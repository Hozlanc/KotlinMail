package com.blake.goodscenter.injection.component

import com.blake.baselibrary.injection.PerComponentScope
import com.blake.baselibrary.injection.component.ActivityComponent
import com.blake.goodscenter.injection.module.CartModule
import com.blake.goodscenter.ui.fragment.CartFragment
import com.blake.goodscenter.ui.fragment.CategoryFragment
import dagger.Component

/**
 * Create by Pidan
 */
@PerComponentScope
@Component(modules = [CartModule::class], dependencies = [ActivityComponent::class])
interface CartComponent {
    fun inject(fragment: CartFragment)
}