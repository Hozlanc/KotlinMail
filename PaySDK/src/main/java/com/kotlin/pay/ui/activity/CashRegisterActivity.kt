package com.kotlin.pay.ui.activity

import android.os.Bundle
import com.blake.baselibrary.ui.activity.BaseMvpActivity
import com.kotlin.pay.R
import com.kotlin.pay.injection.component.DaggerPayComponent
import com.kotlin.pay.injection.module.PayModule
import com.kotlin.pay.presenter.PayPresenter
import com.kotlin.pay.presenter.view.PayView

class CashRegisterActivity : BaseMvpActivity<PayPresenter>(), PayView {
    override fun injectComponent() {
        DaggerPayComponent.builder()
            .activityComponent(mActivityComponent)
            .payModule(PayModule())
            .build()
            .inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_register)
    }

    override fun onGetSignResult(result: String) {
    }

    override fun onPayOrderResult(result: Boolean) {
    }
}