package com.blake.usercenter.presenter

import android.os.Handler
import com.blake.baselibrary.ext.execute
import com.blake.baselibrary.presenter.BasePresenter
import com.blake.baselibrary.rx.BaseSubscriber
import com.blake.usercenter.data.protocol.UserInfo
import com.blake.usercenter.presenter.view.LoginView
import com.blake.usercenter.presenter.view.UserInfoView
import com.blake.usercenter.service.UserService
import javax.inject.Inject

/**
 * Create by Pidan
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {
    @Inject
    lateinit var userService: UserService

    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String) {
        if (!checkNetwork()) return
        mView.showLoading()
        userService.editUser(userIcon, userName, userGender, userSign)
            .execute(lifecycleProvider, object : BaseSubscriber<UserInfo>(mView) {
                override fun onNext(t: UserInfo) {
                    mView.onEditUserResult(t)
                }
            })
    }
}