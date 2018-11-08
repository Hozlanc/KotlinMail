package com.blake.usercenter.injection.component

import com.blake.baselibrary.injection.PerComponentScope
import com.blake.baselibrary.injection.component.ActivityComponent
import com.blake.usercenter.injection.module.UserModule
import com.blake.usercenter.ui.activity.LoginActivity
import com.blake.usercenter.ui.activity.RegisterActivity
import dagger.Component

/**
 * Create by Pidan
 */
@PerComponentScope
@Component(modules = arrayOf(UserModule::class), dependencies = arrayOf(ActivityComponent::class))
interface UserComponent {
    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
}