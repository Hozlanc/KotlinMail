package com.blake.usercenter.injection.component

import com.blake.baselibrary.injection.PerComponentScope
import com.blake.baselibrary.injection.component.ActivityComponent
import com.blake.usercenter.data.protocol.ResetPwdReq
import com.blake.usercenter.injection.module.UploadModule
import com.blake.usercenter.injection.module.UserModule
import com.blake.usercenter.ui.activity.*
import dagger.Component

/**
 * Create by Pidan
 */
@PerComponentScope
@Component(modules = [UserModule::class, UploadModule::class], dependencies = [ActivityComponent::class])
interface UserComponent {
    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: ForgetPwdActivity)
    fun inject(activity: ResetPwdActivity)
    fun inject(activity: UserInfoActivity)
}