package com.blake.baselibrary.ui.fragment

import android.os.Bundle
import com.blake.baselibrary.common.BaseApplication
import com.blake.baselibrary.injection.component.ActivityComponent
import com.blake.baselibrary.injection.component.DaggerActivityComponent
import com.blake.baselibrary.injection.module.ActivityModule
import com.blake.baselibrary.injection.module.LifecycleProviderModule
import com.blake.baselibrary.presenter.BasePresenter
import com.blake.baselibrary.presenter.view.BaseView
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Create by Pidan
 */
abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(text: String) {
        activity.toast(text)
    }

    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjection()
        injectComponent()
    }

    abstract fun injectComponent()

    private fun initInjection() {
        activityComponent =
                DaggerActivityComponent.builder()
                    .appComponent((activity.application as BaseApplication).appComponent)
                    .activityModule(ActivityModule(activity))
                    .lifecycleProviderModule(LifecycleProviderModule(this))
                    .build()
    }
}