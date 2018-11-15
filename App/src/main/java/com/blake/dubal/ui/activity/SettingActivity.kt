package com.blake.dubal.ui.activity

import android.os.Bundle
import com.blake.baselibrary.ext.onClick
import com.blake.baselibrary.ui.activity.BaseActivity
import com.blake.dubal.R
import com.blake.usercenter.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        initView()
    }

    private fun initView() {
        mLogoutBtn.onClick {
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }
}