package com.blake.usercenter.presenter

import com.blake.baselibrary.ext.execute
import com.blake.baselibrary.presenter.BasePresenter
import com.blake.baselibrary.rx.BaseSubscriber
import com.blake.usercenter.presenter.view.RegisterView
import com.blake.usercenter.service.impl.UserServiceImpl

/**
 * Create by Pidan
 */
class RegisterPresenter : BasePresenter<RegisterView>() {
    fun register(mobile: String, verifyCode: String, psw: String) {
        val userService = UserServiceImpl()
        userService.register(mobile, verifyCode, psw)
            .execute(object : BaseSubscriber<Boolean>() {
                override fun onNext(t: Boolean) {
                    mView.registerResult(t)
                }
            })
    }
}