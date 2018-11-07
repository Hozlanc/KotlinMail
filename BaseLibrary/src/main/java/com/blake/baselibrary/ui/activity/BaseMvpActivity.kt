package com.blake.baselibrary.ui.activity

import android.os.Bundle
import com.blake.baselibrary.common.BaseApplication
import com.blake.baselibrary.injection.component.ActivityComponent
import com.blake.baselibrary.injection.component.DaggerActivityComponent
import com.blake.baselibrary.injection.module.ActivityModule
import com.blake.baselibrary.injection.module.LifecycleProviderModule
import com.blake.baselibrary.presenter.BasePresenter
import com.blake.baselibrary.presenter.view.BaseView
import com.blake.baselibrary.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Create by Pidan
 */
open abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {
    override fun showLoading() {
        progressLoading.showLoading()
    }

    override fun hideLoading() {
        progressLoading.hideLoading()
    }

    override fun onError(text: String) {
        toast(text)
    }

    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    private lateinit var progressLoading: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjection()
        injectComponent()

        progressLoading = ProgressLoading.create(this)
    }

    abstract fun injectComponent()

    private fun initInjection() {
        activityComponent =
                DaggerActivityComponent.builder()
                    .appComponent((application as BaseApplication).appComponent)
                    .activityModule(ActivityModule(this))
                    .lifecycleProviderModule(LifecycleProviderModule(this))
                    .build()
    }
}