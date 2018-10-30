package com.blake.baselibrary.presenter

import com.blake.baselibrary.presenter.view.BaseView

/**
 * Create by Pidan
 */
open class BasePresenter<T : BaseView> {
    lateinit var mView: T
}