package com.blake.usercenter.presenter.view

import com.blake.baselibrary.presenter.view.BaseView
import com.blake.usercenter.data.protocol.UserInfo

/**
 * Create by Pidan
 */
interface LoginView : BaseView {
    fun onLoginResult(result: UserInfo)
}