package com.blake.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.blake.baselibrary.ext.enable
import com.blake.baselibrary.ext.onClick
import com.blake.baselibrary.ui.activity.BaseMvpActivity
import com.blake.usercenter.R
import com.blake.usercenter.injection.component.DaggerUserComponent
import com.blake.usercenter.injection.module.UserModule
import com.blake.usercenter.presenter.ForgetPwdPresenter
import com.blake.usercenter.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证码成功")
            }
            R.id.mNextBtn -> mPresenter.forgetPwd(
                mMobileEt.text.toString(),
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

    override fun onForgetPwdResult(result: String) {
        println(result)
        startActivity<ResetPwdActivity>("mobile" to mMobileEt.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

        initView()
    }

    private fun initView() {
        mNextBtn.enable(mMobileEt, ::isBtnEnable)
        mNextBtn.enable(mVerifyCodeEt, ::isBtnEnable)

        mNextBtn.onClick(this)
        mVerifyCodeBtn.onClick(this)
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() and
                mVerifyCodeEt.text.isNullOrEmpty().not()
    }
}
