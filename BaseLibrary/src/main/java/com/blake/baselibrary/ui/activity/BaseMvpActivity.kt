package com.blake.baselibrary.ui.activity

import com.blake.baselibrary.presenter.BasePresenter
import com.blake.baselibrary.presenter.view.BaseView

/**
 * Create by Pidan
 */
open class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    lateinit var mPresenter: T
}