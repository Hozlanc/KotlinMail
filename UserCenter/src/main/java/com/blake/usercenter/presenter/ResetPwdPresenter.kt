package com.blake.usercenter.presenter

import com.blake.baselibrary.ext.execute
import com.blake.baselibrary.presenter.BasePresenter
import com.blake.baselibrary.rx.BaseSubscriber
import com.blake.usercenter.presenter.view.ResetPwdView
import com.blake.usercenter.service.UserService
import javax.inject.Inject

/**
 * Create by Pidan
 */
class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {
    @Inject
    lateinit var userService: UserService

    fun resetPwd(mobile: String, pwd: String) {
        if (!checkNetwork()) {
            return
        }
        mView.showLoading()
        userService.resetPwd(mobile, pwd)
            .execute(lifecycleProvider, object : BaseSubscriber<Boolean>(mView) {
                override fun onNext(t: Boolean) {
                    if (t) mView.onResetPwdResult("重置密码成功")
                }
            })
    }
}