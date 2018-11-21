package com.blake.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.blake.baselibrary.ext.enable
import com.blake.baselibrary.ext.onClick
import com.blake.baselibrary.ui.activity.BaseMvpActivity
import com.blake.usercenter.R
import com.blake.usercenter.injection.component.DaggerUserComponent
import com.blake.usercenter.injection.module.UserModule
import com.blake.usercenter.presenter.RegisterPresenter
import com.blake.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {
    override fun onClick(v: View) {
        when (v.id) {
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证码成功")
            }
            R.id.mRegisterBtn -> mPresenter.register(
                mMobileEt.text.toString(),
                mPwdEt.text.toString(),
                mVerifyCodeEt.text.toString()
            )
            else -> return
        }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
            .activityComponent(mActivityComponent)
            .userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this
    }

    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
    }

    private fun initView() {
        mRegisterBtn.enable(mMobileEt, ::isBtnEnable)
        mRegisterBtn.enable(mVerifyCodeEt, ::isBtnEnable)
        mRegisterBtn.enable(mPwdEt, ::isBtnEnable)
        mRegisterBtn.enable(mPwdConfirmEt, ::isBtnEnable)

        mRegisterBtn.onClick(this)
        mVerifyCodeBtn.onClick(this)
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() and
                mVerifyCodeEt.text.isNullOrEmpty().not() and
                mPwdEt.text.isNullOrEmpty().not() and
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }
}
