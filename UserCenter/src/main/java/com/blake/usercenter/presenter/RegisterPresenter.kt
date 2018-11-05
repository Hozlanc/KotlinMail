package com.blake.usercenter.presenter

import com.blake.baselibrary.ext.execute
import com.blake.baselibrary.presenter.BasePresenter
import com.blake.baselibrary.rx.BaseSubscriber
import com.blake.usercenter.presenter.view.RegisterView
import com.blake.usercenter.service.UserService
import com.blake.usercenter.service.impl.UserServiceImpl
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

/**
 * Create by Pidan
 */
class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {
    @Inject
    lateinit var userService: UserService

    fun register(mobile: String, verifyCode: String, psw: String) {
        userService.register(mobile, verifyCode, psw)
            .execute(lifecycleProvider, object : BaseSubscriber<Boolean>() {
                override fun onNext(t: Boolean) {
                    if (t)
                        mView.registerResult("注册成功")
                }
            })
    }
}