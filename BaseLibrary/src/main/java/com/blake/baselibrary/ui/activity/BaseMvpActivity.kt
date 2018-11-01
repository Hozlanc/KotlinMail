package com.blake.baselibrary.ui.activity

import android.os.Bundle
import com.blake.baselibrary.common.BaseApplication
import com.blake.baselibrary.injection.component.ActivityComponent
import com.blake.baselibrary.injection.component.DaggerActivityComponent
import com.blake.baselibrary.injection.module.ActivityModule
import com.blake.baselibrary.presenter.BasePresenter
import com.blake.baselibrary.presenter.view.BaseView
import javax.inject.Inject

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

    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjection()
    }

    private fun initInjection() {
        activityComponent =
                DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent)
                    .activityModule(ActivityModule(this)).build()
    }
}