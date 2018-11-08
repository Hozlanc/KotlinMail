package com.blake.usercenter.presenter

import android.os.Handler
import com.blake.baselibrary.ext.execute
import com.blake.baselibrary.presenter.BasePresenter
import com.blake.baselibrary.rx.BaseSubscriber
import com.blake.usercenter.data.protocol.UserInfo
import com.blake.usercenter.presenter.view.LoginView
import com.blake.usercenter.service.UserService
import javax.inject.Inject

/**
 * Create by Pidan
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {
    @Inject
    lateinit var userService: UserService

    fun login(mobile: String, psw: String, pushId: String) {
        if (!checkNetwork()) {
            return
        }
        mView.showLoading()
        Handler().postDelayed({
            userService.login(mobile, psw, pushId)
                .execute(lifecycleProvider, object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        mView.onLoginResult(t)
                    }
                })
        }, 2000)
    }
}