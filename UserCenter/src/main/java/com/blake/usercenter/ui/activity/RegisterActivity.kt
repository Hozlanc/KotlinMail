package com.blake.usercenter.ui.activity

import android.os.Bundle
import com.blake.baselibrary.ui.activity.BaseMvpActivity
import com.blake.usercenter.presenter.RegisterPresenter
import com.blake.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {
    override fun registerResult(result: Boolean) {
        toast("Register Success")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.blake.usercenter.R.layout.activity_register)

        mPresenter = RegisterPresenter()
        mPresenter.mView = this

        mRegisterBtn.setOnClickListener {
            mPresenter.register("", "", "")
        }
    }
}
