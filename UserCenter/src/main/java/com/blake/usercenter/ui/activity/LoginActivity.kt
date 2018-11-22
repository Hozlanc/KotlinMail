package com.blake.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.blake.baselibrary.ext.enable
import com.blake.baselibrary.ext.onClick
import com.blake.baselibrary.ui.activity.BaseMvpActivity
import com.blake.provider.router.RouterPath
import com.blake.usercenter.R
import com.blake.usercenter.data.protocol.UserInfo
import com.blake.usercenter.injection.component.DaggerUserComponent
import com.blake.usercenter.injection.module.UserModule
import com.blake.usercenter.presenter.LoginPresenter
import com.blake.usercenter.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

@Route(path = RouterPath.UserCenter.PATH_LOGIN)
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {
    override fun injectComponent() {
        DaggerUserComponent.builder()
            .activityComponent(mActivityComponent)
            .userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    private fun initView() {
        mLoginBtn.enable(mMobileEt, ::isBtnEnable)
        mLoginBtn.enable(mPwdEt, ::isBtnEnable)

        mHeaderBar.getRightView().onClick(this)
        mLoginBtn.onClick(this)
        mForgetPwdTv.onClick(this)

        mMobileEt.setText("123")
        mPwdEt.setText("123")
    }

    private var pressTime: Long = 0

//    override fun onBackPressed() {
//        val time = System.currentTimeMillis()
//        if (time - pressTime > 2000) {
//            toast("再按一次退出程序")
//            pressTime = time
//        } else {
//            AppManager.exitApp(this)
//        }
//    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mRightTv -> startActivity<RegisterActivity>()
            R.id.mLoginBtn -> mPresenter.login(mMobileEt.text.toString(), mPwdEt.text.toString(), "")
            R.id.mForgetPwdTv -> startActivity<ForgetPwdActivity>()
            else -> return
        }
    }

    override fun onLoginResult(result: UserInfo) {
        finish()
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() and
                mPwdEt.text.isNullOrEmpty().not()
    }
}
