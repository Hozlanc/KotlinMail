package com.blake.usercenter.presenter

import android.os.Handler
import com.blake.baselibrary.ext.execute
import com.blake.baselibrary.presenter.BasePresenter
import com.blake.baselibrary.rx.BaseSubscriber
import com.blake.usercenter.presenter.view.ForgetPwdView
import com.blake.usercenter.presenter.view.RegisterView
import com.blake.usercenter.service.UserService
import javax.inject.Inject

/**
 * Create by Pidan
 */
class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {
    @Inject
    lateinit var userService: UserService

    fun forgetPwd(mobile: String, verifyCode: String) {
        if (!checkNetwork()) {
            return
        }
        mView.showLoading()
        userService.forgetPwd(mobile, verifyCode)
            .execute(lifecycleProvider, object : BaseSubscriber<Boolean>(mView) {
                override fun onNext(t: Boolean) {
                    if (t) mView.onForgetPwdResult("验证成功")
                }
            })
    }
}