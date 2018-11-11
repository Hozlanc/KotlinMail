package com.blake.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.blake.baselibrary.common.AppManager
import com.blake.baselibrary.ext.enable
import com.blake.baselibrary.ext.onClick
import com.blake.baselibrary.ui.activity.BaseMvpActivity
import com.blake.usercenter.R
import com.blake.usercenter.injection.component.DaggerUserComponent
import com.blake.usercenter.injection.module.UserModule
import com.blake.usercenter.presenter.RegisterPresenter
import com.blake.usercenter.presenter.UserInfoPresenter
import com.blake.usercenter.presenter.view.RegisterView
import com.blake.usercenter.presenter.view.UserInfoView
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.model.TResult


class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, View.OnClickListener,
    TakePhoto.TakeResultListener {
    var list = ArrayList()
    lateinit var mTakePhoto: TakePhoto

    override fun onClick(v: View) {
        when (v.id) {

            else -> return
        }
    }




    li
    override fun injectComponent() {
        DaggerUserComponent.builder()
            .activityComponent(activityComponent)
            .userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        initView()
    }

    private fun initView() {
        mUserIconIv.onClick { showAlertView() }
    }

    private fun showAlertView() {
        AlertView.Builder().setContext(this)
            .setStyle(AlertView.Style.ActionSheet)
            .setTitle("选择图片")
            .setMessage(null)
            .setCancelText("取消")
            .setDestructive("拍照", "从相册中选择")
            .setOthers(null)
            .setOnItemClickListener(object : OnItemClickListener {
                override fun onItemClick(o: Any?, position: Int) {
                    when (position) {
                        0 -> toast("拍照")
                        1 -> mTakePhoto.onPickFromGallery()
                    }
                }
            }).build()
            .show()

    }

    override fun takeSuccess(result: TResult?) {
    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
    }
}
