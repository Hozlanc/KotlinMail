package com.blake.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.blake.baselibrary.ext.enable
import com.blake.baselibrary.ext.onClick
import com.blake.baselibrary.ui.activity.BaseMvpActivity
import com.blake.usercenter.R
import com.blake.usercenter.R.id.*
import com.blake.usercenter.injection.component.DaggerUserComponent
import com.blake.usercenter.injection.module.UserModule
import com.blake.usercenter.presenter.ResetPwdPresenter
import com.blake.usercenter.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.*

class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView {
    override fun injectComponent() {
        DaggerUserComponent.builder()
            .activityComponent(activityComponent)
            .userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this
    }

    override fun onResetPwdResult(result: String) {
        if (mPwdEt.text.toString() != mPwdConfirmEt.text.toString()) {
            toast("两次输入密码不一致")
            return
        }
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)

        initView()
    }

    private fun initView() {
        mConfirmBtn.enable(mPwdEt, ::isBtnEnable)
        mConfirmBtn.enable(mPwdConfirmEt, ::isBtnEnable)

        mConfirmBtn.onClick { mPresenter.resetPwd(intent.getStringExtra("mobile"), mPwdEt.text.toString()) }
    }

    private fun isBtnEnable(): Boolean {
        return mPwdEt.text.isNullOrEmpty().not() and
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }
}
