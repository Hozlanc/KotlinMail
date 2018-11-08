package com.blake.usercenter.presenter

import android.os.Handler
import com.blake.baselibrary.ext.execute
import com.blake.baselibrary.presenter.BasePresenter
import com.blake.baselibrary.rx.BaseSubscriber
import com.blake.usercenter.presenter.view.RegisterView
import com.blake.usercenter.service.UserService
import javax.inject.Inject

/**
 * Create by Pidan
 */
class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {
    @Inject
    lateinit var userService: UserService

    fun register(mobile: String, psw: String, verifyCode: String) {
        if (!checkNetwork()) {
            return
        }
        mView.showLoading()
        Handler().postDelayed({
            userService.register(mobile, psw, verifyCode)
                .execute(lifecycleProvider, object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t) mView.onRegisterResult("注册成功")
                    }
                })
        }, 2000)
    }
}