package com.blake.usercenter.presenter.view

import com.blake.baselibrary.presenter.view.BaseView

/**
 * Create by Pidan
 */
interface UserInfoView : BaseView {
    fun onGetUploadTokenResult(result: String)
}